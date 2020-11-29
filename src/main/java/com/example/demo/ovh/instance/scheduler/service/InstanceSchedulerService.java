package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.feign.common.IpAddressApi;
import com.example.demo.ovh.feign.instance.InstanceClient;
import com.example.demo.ovh.feign.instance.model.InstanceApi;
import com.example.demo.ovh.instance.aggregate.command.InstanceUpdateCommand;
import com.example.demo.ovh.instance.entity.InstanceEntity;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.ovh.instance.entity.mapper.InstanceMapper;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstanceSchedulerService implements IInstanceSchedulerService {

    private final OvhConfig ovhConfig;
    private final JPQLQueryFactory queryFactory;
    private final InstanceClient instanceClient;
    private final CommandGateway commandGateway;

    @Override
    public List<UUID> handleInstanceUpdates() {

        List<UUID> updatedInstances = new ArrayList<>();

        for(List<InstanceApi> apiResponses : getPartitionedApiList()) {

            List<String> instanceIds = getInstanceIds(apiResponses);

            ImmutableList<Instance> instances = getInstances(instanceIds);

            if(CollectionUtils.isNotEmpty(instances)) {

                for(InstanceApi apiResponse : apiResponses) {

                    for (Instance instance : instances) {

                        if (apiResponse.getId().equals(instance.getInstanceId()) && !instance.getStatus().equals(InstanceStatus.BUILD)) {

                            if (!isEqual(instance, apiResponse)) {

                                updatedInstances.add(handleInstanceUpdate(instance.getId(), apiResponse));
                            }
                        }
                    }
                }
            }
        }

        return updatedInstances;
    }

    public List<List<InstanceApi>> getPartitionedApiList() {

        return Lists.partition(instanceClient.getInstances(ovhConfig.getProjectId()), 20);
    }

    public ImmutableList<Instance> getInstances(Collection<String> ids) {

        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        //TODO: Replace with QueryGate / Projection
        List<InstanceEntity> entities = queryFactory.select(qInstance)
                .from(qInstance)
                .where(qInstance.instanceId.in(ids))
                .fetch();

        return InstanceMapper.map(entities);
    }

    private List<String> getInstanceIds(List<InstanceApi> apiResponses) {

        return apiResponses.stream()
                .map(InstanceApi::getId)
                .collect(Collectors.toList());
    }

    private boolean isEqual(Instance instance, InstanceApi apiResponse) {

        if(!Objects.equals(instance.getName(), apiResponse.getName())) return false;
        if(!Objects.equals(instance.getStatus(), apiResponse.getStatus())) return false;
        if(!Objects.equals(instance.getInstanceCreatedDate(), apiResponse.getCreatedDate())) return false;

        String ip4Address = getIp4Address(apiResponse.getIpAddresses());
        if(!Objects.equals(instance.getIp4Address(), ip4Address)) return false;

        String ip6Address = getIp6Address(apiResponse.getIpAddresses());;
        return Objects.equals(instance.getIp6Address(), ip6Address);
    }

    private String getIp4Address(List<IpAddressApi> ipAddresses) {

        return getIpAddress(ipAddresses, 4);
    }

    private String getIp6Address(List<IpAddressApi> ipAddresses) {

        return getIpAddress(ipAddresses, 6);
    }

    private String getIpAddress(List<IpAddressApi> ipAddresses, Integer version) {

        if(ipAddresses == null) {

            return null;
        }

        return ipAddresses.stream()
                .filter(ipAddress -> ipAddress.getVersion().equals(version))
                .map(IpAddressApi::getIp)
                .findFirst()
                .orElse(null);
    }

    private UUID handleInstanceUpdate(String id, InstanceApi apiResponse) {

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .id(UUID.fromString(id))
                .name(apiResponse.getName())
                .status(apiResponse.getStatus())
                .instanceCreatedDate(apiResponse.getCreatedDate())
                .ip4Address(getIp4Address(apiResponse.getIpAddresses()))
                .ip6Address(getIp6Address(apiResponse.getIpAddresses()))
                .build();

        return commandGateway.sendAndWait(command);
    }
}
