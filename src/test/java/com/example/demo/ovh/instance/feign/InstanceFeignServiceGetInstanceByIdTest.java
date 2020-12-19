package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InstanceFeignServiceGetInstanceByIdTest {

    private OvhConfig ovhConfig;
    private IInstanceClient instanceClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceClient = Mockito.mock(IInstanceClient.class);
    }

    @Test
    public void whenServiceGetInstanceByIdThenExpectInstance() {

        Mockito.when(instanceClient.getInstanceById(Mockito.anyString(), Mockito.anyString())).thenReturn(new InstanceApi());

        InstanceFeignService service = new InstanceFeignService(ovhConfig, instanceClient);

        Assertions.assertEquals(new InstanceApi(), service.getInstanceById("instanceId"));
    }
}
