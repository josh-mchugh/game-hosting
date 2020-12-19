package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceCreateApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InstanceFeignServiceCreateInstanceTest {

    private OvhConfig ovhConfig;
    private IInstanceClient instanceClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceClient = Mockito.mock(IInstanceClient.class);
    }

    @Test
    public void whenServiceCreateInstanceThenReturnInstance() {

        Mockito.when(instanceClient.createInstance(Mockito.anyString(), Mockito.any())).thenReturn(new InstanceApi());

        InstanceFeignService service = new InstanceFeignService(ovhConfig, instanceClient);

        Assertions.assertEquals(new InstanceApi(), service.createInstance(InstanceCreateApi.builder().build()));
    }
}
