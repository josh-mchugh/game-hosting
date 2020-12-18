package com.example.demo.ovh.flavor.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class FlavorFeignServiceGetFlavorsTest {

    private OvhConfig ovhConfig;
    private IFlavorClient flavorClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        flavorClient = Mockito.mock(IFlavorClient.class);
    }

    @Test
    public void whenServiceGetFlavorsThenReturnFlavors() {

        List<FlavorApi> expected = Arrays.asList(new FlavorApi(), new FlavorApi());

        Mockito.when(flavorClient.getFlavors(Mockito.anyString())).thenReturn(expected);

        IFlavorFeignService flavorFeignService = new FlavorFeignService(ovhConfig, flavorClient);

        Assertions.assertEquals(expected, flavorFeignService.getFlavors());
    }
}
