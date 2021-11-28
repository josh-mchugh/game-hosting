package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.ovh.instance.aggregate.command.InstanceUpdateCommand;
import com.example.demo.ovh.instance.feign.InstanceFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsQuery;
import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsResponse;
import com.example.demo.ovh.instance.scheduler.projection.projection.InstanceProjection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class InstanceSchedulerServiceImpl implements InstanceSchedulerService {

    private final InstanceFeignService instanceFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<InstanceApi> getInstanceApis() {

        return ImmutableList.copyOf(instanceFeignService.getInstances());
    }

    @Override
    public List<UUID> handleInstanceUpdates(ImmutableList<InstanceApi> instanceApis) throws ExecutionException, InterruptedException {

        List<UUID> updatedInstances = new ArrayList<>();

        for(List<InstanceApi> apiResponses : getPartitionedApiList(instanceApis)) {

            ImmutableList<String> instanceIds = getInstanceIds(apiResponses);

            ImmutableList<InstanceProjection> instances = getInstances(instanceIds);

            if(CollectionUtils.isNotEmpty(instances)) {

                for(InstanceApi api : apiResponses) {

                    for (InstanceProjection instance : instances) {

                        if (api.getId().equals(instance.getOvhId())) {

                            if (isDifferent(instance, api)) {

                                updatedInstances.add(handleInstanceUpdate(instance.getId(), api));
                            }
                        }
                    }
                }
            }
        }

        return updatedInstances;
    }

    public List<List<InstanceApi>> getPartitionedApiList(List<InstanceApi> instanceApis) {

        return Lists.partition(instanceApis, 20);
    }

    public ImmutableList<InstanceProjection> getInstances(ImmutableList<String> ids) throws ExecutionException, InterruptedException {

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(ids);
        FetchInstancesByOvhIdsResponse response = queryGateway.query(query, FetchInstancesByOvhIdsResponse.class).get();

        return response.getInstances();
    }

    private ImmutableList<String> getInstanceIds(List<InstanceApi> apiResponses) {

        return apiResponses.stream()
                .map(InstanceApi::getId)
                .collect(ImmutableList.toImmutableList());
    }

    private boolean isDifferent(InstanceProjection instance, InstanceApi api) {

        if(!StringUtils.equals(instance.getName(), api.getName())) return true;
        if(!Objects.equals(instance.getInstanceCreatedDate(), api.getCreatedDate())) return true;
        if(!Objects.equals(instance.getStatus(), api.getStatus())) return true;
        if(!StringUtils.equals(instance.getIp4Address(), api.getIp4Address())) return true;

        return !StringUtils.equals(instance.getIp6Address(), api.getIp6Address());
    }

    private UUID handleInstanceUpdate(UUID id, InstanceApi apiResponse) {

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .id(id)
                .name(apiResponse.getName())
                .status(apiResponse.getStatus())
                .instanceCreatedDate(apiResponse.getCreatedDate())
                .ip4Address(apiResponse.getIp4Address())
                .ip6Address(apiResponse.getIp6Address())
                .build();

        return commandGateway.sendAndWait(command);
    }
}
