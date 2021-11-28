package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InstanceGroupFeignServiceDeleteInstanceGroupByIdTest {

    private OvhConfig ovhConfig;
    private InstanceGroupClient instanceGroupClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceGroupClient = Mockito.mock(InstanceGroupClient.class);
    }

    @Test
    public void whenServiceDeleteInstanceGroupByIdThenExpectNoException() {

        Mockito.doNothing().when(instanceGroupClient).deleteInstanceGroupById(Mockito.anyString(), Mockito.anyString());

        InstanceGroupFeignServiceImpl service = new InstanceGroupFeignServiceImpl(ovhConfig, instanceGroupClient);

        Assertions.assertDoesNotThrow(() -> service.deleteInstanceGroupById("groupId"));
    }
}
