package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.ovh.feign.common.IpAddressApi;
import com.example.demo.ovh.feign.instance.InstanceClient;
import com.example.demo.ovh.feign.instance.model.InstanceApi;
import com.example.demo.ovh.instance.aggregate.event.InstanceUpdatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.entity.service.IInstanceService;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.google.common.collect.Lists;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
public class InstanceSchedulerServiceTest {

    @Autowired
    private IInstanceSchedulerService instanceSchedulerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IInstanceService instanceService;

    @MockBean
    private InstanceClient instanceClient;

    @MockBean
    private CommandGateway commandGateway;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.createDefault();
    }

    @Test
    public void whenApiResponseIsEqualThenReturnEmptyArray() {

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList());

        List<UUID> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(0, updatedInstances.size());
    }

    @Test
    public void whenApiResponseNameIsDifferentThenArrayWithItems() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName("new-name");
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseStatusIsDifferentThenArrayWithItems() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(InstanceStatus.STOPPED);
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseCreatedDateIdDifferentThenArrayWithItems() {

        LocalDateTime createdDate = LocalDateTime.now();

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(createdDate);

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseIp4AddressIdDifferentThenArrayWithItems() {

        InstanceUpdatedEvent updatedEvent = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .name(data.getInstance().getName())
                .ip4Address("0.0.0.0.0.0")
                .ip6Address("0.0.0.0.0.0")
                .status(data.getInstance().getStatus())
                .instanceCreatedDate(data.getInstance().getInstanceCreatedDate())
                .build();

        instanceService.handleUpdated(updatedEvent);

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses("1.1.1.1.1", "0.0.0.0.0.0"));
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseIp6AddressIdDifferentThenArrayWithItems() {

        InstanceUpdatedEvent updatedEvent = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .name(data.getInstance().getName())
                .ip4Address("0.0.0.0.0.0")
                .ip6Address("0.0.0.0.0.0")
                .status(data.getInstance().getStatus())
                .instanceCreatedDate(data.getInstance().getInstanceCreatedDate())
                .build();

        instanceService.handleUpdated(updatedEvent);

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses("0.0.0.0.0.0", "1.1.1.1.1"));
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenApiResponseHasNullIpAddressThenArrayWithItems() {

        InstanceUpdatedEvent updatedEvent = InstanceUpdatedEvent.builder()
                .id(UUID.fromString(data.getInstance().getId()))
                .name(data.getInstance().getName())
                .ip4Address("0.0.0.0.0.0")
                .ip6Address("0.0.0.0.0.0")
                .status(data.getInstance().getStatus())
                .instanceCreatedDate(data.getInstance().getInstanceCreatedDate())
                .build();

        instanceService.handleUpdated(updatedEvent);

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(null);
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(1, instances.size());
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
