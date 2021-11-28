package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupCreateApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InstanceGroupFeignServiceCreateInstanceGroupTest {

    private OvhConfig ovhConfig;
    private InstanceGroupClient instanceGroupClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceGroupClient = Mockito.mock(InstanceGroupClient.class);
    }

    @Test
    public void whenServiceCreatesInstanceGroupThenExpectInstanceGroup() {

        Mockito.when(instanceGroupClient.createInstanceGroup(Mockito.anyString(), Mockito.any())).thenReturn(new InstanceGroupApi());

        InstanceGroupFeignServiceImpl service = new InstanceGroupFeignServiceImpl(ovhConfig, instanceGroupClient);

        Assertions.assertEquals(new InstanceGroupApi(), service.createInstanceGroup(InstanceGroupCreateApi.builder().build()));
    }
}
