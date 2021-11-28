package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InstanceFeignServiceStopInstanceTest {

    private OvhConfig ovhConfig;
    private InstanceClient instanceClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceClient = Mockito.mock(InstanceClient.class);
    }

    @Test
    public void whenServiceStopInstanceThenExpectNoExceptions() {

        Mockito.doNothing().when(instanceClient).stopInstance(Mockito.anyString(), Mockito.anyString());

        InstanceFeignServiceImpl service = new InstanceFeignServiceImpl(ovhConfig, instanceClient);

        Assertions.assertDoesNotThrow(() -> service.stopInstance("instanceId"));
    }
}
