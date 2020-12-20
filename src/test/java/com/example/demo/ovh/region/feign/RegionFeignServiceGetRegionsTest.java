package com.example.demo.ovh.region.feign;

import com.example.demo.framework.properties.OvhConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class RegionFeignServiceGetRegionsTest {

    private OvhConfig ovhConfig;
    private IRegionClient regionClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        regionClient = Mockito.mock(IRegionClient.class);
    }

    @Test
    public void whenServiceGetRegionsThenReturnRegionNames() {

        Mockito.when(regionClient.getRegions(Mockito.anyString())).thenReturn(Arrays.asList("region1", "region2"));

        RegionFeignService service = new RegionFeignService(ovhConfig, regionClient);

        Assertions.assertEquals(Arrays.asList("region1", "region2"), service.getRegions());
    }
}
