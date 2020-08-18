package com.example.demo.ovh.instance.scheduler;

import com.example.demo.ovh.feign.common.IpAddressApi;
import com.example.demo.ovh.feign.instance.InstanceClient;
import com.example.demo.ovh.feign.instance.model.InstanceApi;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.scheduler.service.IInstanceSchedulerService;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceSchedulerServiceTest {

    @Autowired
    private IInstanceSchedulerService instanceSchedulerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private InstanceClient instanceClient;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.createDefault();
    }

    @Test
    public void whenApiResponsesIsEmptyThenReturnEmptyArray() {

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(new ArrayList<>());

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(0, updatedInstances.size());
    }

    @Test
    public void whenApiResponseIsEqualThenReturnEmptyArray() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(0, updatedInstances.size());

    }

    @Test
    public void whenApiResponseInstanceIdIsValidThenEqualUpdatedInstanceId() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> instances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(apiResponse.getId(), instances.get(0).getInstanceId());
    }

    @Test
    public void whenApiResponseNameIsDifferentThenReturnUpdatedName() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName("new-name");
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals("new-name", updatedInstances.get(0).getName());
    }

    @Test
    public void whenApiResponseStatusIsDifferentThenReturnUpdatedStatus() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(InstanceStatus.STOPPED);
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(InstanceStatus.STOPPED, updatedInstances.get(0).getStatus());
    }

    @Test
    public void whenApiResponseCreatedDateIdDifferentThenReturnUpdatedCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses());
        apiResponse.setCreatedDate(createdDate);

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals(createdDate, updatedInstances.get(0).getInstanceCreatedDate());
    }

    @Test
    public void whenApiResponseIp4AddressIdDifferentThenReturnUpdatedIp4Address() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses("1.1.1.1.1", "0.0.0.0.0.0"));
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals("1.1.1.1.1", updatedInstances.get(0).getIp4Address());
    }

    @Test
    public void whenApiResponseIp6AddressIdDifferentThenReturnUpdatedIp6Address() {

        InstanceApi apiResponse = new InstanceApi();
        apiResponse.setId(data.getInstance().getInstanceId());
        apiResponse.setName(data.getInstance().getName());
        apiResponse.setStatus(data.getInstance().getStatus());
        apiResponse.setIpAddresses(buildIpAddresses("0.0.0.0.0.0", "1.1.1.1.1"));
        apiResponse.setCreatedDate(data.getInstance().getInstanceCreatedDate());

        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(Lists.newArrayList(apiResponse));

        ImmutableList<Instance> updatedInstances = instanceSchedulerService.handleInstanceUpdates();

        Assertions.assertEquals("1.1.1.1.1", updatedInstances.get(0).getIp6Address());
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
