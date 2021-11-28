package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class InstanceGroupFeignServiceGetInstanceGroupsTest {

    private OvhConfig ovhConfig;
    private InstanceGroupClient instanceGroupClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceGroupClient = Mockito.mock(InstanceGroupClient.class);
    }

    @Test
    public void whenServiceGetInstanceGroupsThenExpectInstanceGroups() {

        Mockito.when(instanceGroupClient.getInstanceGroups(Mockito.anyString())).thenReturn(Arrays.asList(new InstanceGroupApi(), new InstanceGroupApi()));

        InstanceGroupFeignServiceImpl service = new InstanceGroupFeignServiceImpl(ovhConfig, instanceGroupClient);

        Assertions.assertEquals(Arrays.asList(new InstanceGroupApi(), new InstanceGroupApi()), service.getInstanceGroups());
    }
}
