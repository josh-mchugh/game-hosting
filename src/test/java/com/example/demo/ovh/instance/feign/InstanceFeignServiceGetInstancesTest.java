package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class InstanceFeignServiceGetInstancesTest {

    private OvhConfig ovhConfig;
    private InstanceClient instanceClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        instanceClient = Mockito.mock(InstanceClient.class);
    }

    @Test
    public void whenServiceGetInstancesThenReturnInstances() {

        List<InstanceApi> mockResults = Arrays.asList(new InstanceApi(), new InstanceApi());
        Mockito.when(instanceClient.getInstances(Mockito.anyString())).thenReturn(mockResults);

        InstanceFeignServiceImpl service = new InstanceFeignServiceImpl(ovhConfig, instanceClient);

        Assertions.assertEquals(Arrays.asList(new InstanceApi(), new InstanceApi()), service.getInstances());
    }
}
