package com.example.demo.ovh.region.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.region.feign.model.RegionApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RegionFeignServiceGetRegionTest {

    private OvhConfig ovhConfig;
    private RegionClient regionClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        regionClient = Mockito.mock(RegionClient.class);
    }

    @Test
    public void whenServiceGetRegionThenReturnRegionApi() {

        Mockito.when(regionClient.getRegion(Mockito.anyString(), Mockito.anyString())).thenReturn(new RegionApi());

        RegionFeignServiceImpl service = new RegionFeignServiceImpl(ovhConfig, regionClient);

        Assertions.assertEquals(new RegionApi(), service.getRegion("regionName"));
    }
}
