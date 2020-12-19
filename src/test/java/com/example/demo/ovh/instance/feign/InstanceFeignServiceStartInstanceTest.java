package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InstanceFeignServiceStartInstanceTest {

    private OvhConfig ovhConfig;
    private IInstanceClient instanceClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceClient = Mockito.mock(IInstanceClient.class);
    }

    @Test
    public void whenServiceStartInstanceThenExpectNoExceptions() {

        Mockito.doNothing().when(instanceClient).startInstance(Mockito.anyString(), Mockito.anyString());

        InstanceFeignService service = new InstanceFeignService(ovhConfig, instanceClient);

        Assertions.assertDoesNotThrow(() -> service.startInstance("instanceId"));
    }
}
