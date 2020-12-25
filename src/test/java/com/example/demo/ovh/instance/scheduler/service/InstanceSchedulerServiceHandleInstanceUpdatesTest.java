package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceUpdatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.entity.service.IInstanceService;
import com.example.demo.ovh.instance.feign.IInstanceFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.IpAddressApi;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceSchedulerServiceHandleInstanceUpdatesTest {

    @Autowired
    private IInstanceSchedulerService instanceSchedulerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IInstanceService instanceService;

    @MockBean
    private IInstanceFeignService instanceFeignService;

    @MockBean
    private CommandGateway commandGateway;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.createDefault();
    }

    @Test
    public void whenApiResponseIsEqualThenReturnEmptyArray() {

        List<UUID> updatedInstances = instanceSchedulerService.handleInstanceUpdates(ImmutableList.of());

        Assertions.assertEquals(0, updatedInstances.size());
    }

    @Test
    public void whenApiResponseNameIsDifferentThenArrayWithItems() {

        InstanceApi instanceApi = instanceApi();
        instanceApi.setName("new-name");

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates(ImmutableList.of(instanceApi));

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseStatusIsDifferentThenArrayWithItems() {

        InstanceUpdatedEvent updatedEvent = instanceCreatedEvent();
        instanceService.handleUpdated(updatedEvent);

        InstanceApi instanceApi = instanceApi();
        instanceApi.setStatus(InstanceStatus.STOPPED);

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates(ImmutableList.of(instanceApi));

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseCreatedDateIdDifferentThenArrayWithItems() {

        InstanceApi instanceApi = instanceApi();
        instanceApi.setCreatedDate(LocalDateTime.now());

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates(ImmutableList.of(instanceApi));

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseIp4AddressIdDifferentThenArrayWithItems() {

        InstanceUpdatedEvent updatedEvent = instanceCreatedEvent();
        instanceService.handleUpdated(updatedEvent);

        InstanceApi instanceApi = instanceApi();
        instanceApi.setIpAddresses(buildIpAddresses("1.1.1.1.1", "0.0.0.0.0.0"));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates(ImmutableList.of(instanceApi));

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseIp6AddressIdDifferentThenArrayWithItems() {

        InstanceUpdatedEvent updatedEvent = instanceCreatedEvent();
        instanceService.handleUpdated(updatedEvent);

        InstanceApi instanceApi = instanceApi();
        instanceApi.setIpAddresses(buildIpAddresses("0.0.0.0.0.0", "1.1.1.1.1"));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates(ImmutableList.of(instanceApi));

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseHasNullIpAddressThenArrayWithItems() {

        InstanceUpdatedEvent updatedEvent = instanceCreatedEvent();
        instanceService.handleUpdated(updatedEvent);

        InstanceApi instanceApi = instanceApi();
        instanceApi.setIpAddresses(null);

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates(ImmutableList.of(instanceApi));

        Assertions.assertEquals(1, instances.size());
    }

    private InstanceUpdatedEvent instanceCreatedEvent() {

        return InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .name(data.getInstance().getName())
                .ip4Address("0.0.0.0.0.0")
                .ip6Address("0.0.0.0.0.0")
                .status(data.getInstance().getStatus())
                .instanceCreatedDate(data.getInstance().getInstanceCreatedDate())
                .build();
    }

    private InstanceApi instanceApi() {

        InstanceApi instanceApi = new InstanceApi();
        instanceApi.setId("ovhId");
        instanceApi.setName("name");
        instanceApi.setStatus(InstanceStatus.ACTIVE);
        instanceApi.setCreatedDate(LocalDateTime.of(2020, 12,23, 22, 30));
        instanceApi.setIpAddresses(buildIpAddresses());

        return instanceApi;
    }

    private List<IpAddressApi> buildIpAddresses() {

        return buildIpAddresses("0.0.0.0.0.0", "0.0.0.0.0.0");
    }

    private List<IpAddressApi> buildIpAddresses(String ip4Address, String ip6Address) {

        IpAddressApi ip4 = new IpAddressApi();
        ip4.setVersion(4);
        ip4.setIp(ip4Address);

        IpAddressApi ip6 = new IpAddressApi();
        ip6.setVersion(6);
        ip6.setIp(ip6Address);

        return Lists.newArrayList(ip4, ip6);
    }
}
