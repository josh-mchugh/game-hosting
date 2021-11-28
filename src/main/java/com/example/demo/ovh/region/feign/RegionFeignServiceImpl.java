package com.example.demo.ovh.region.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.region.feign.model.RegionApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionFeignServiceImpl implements RegionFeignService {

    private final OvhConfig ovhConfig;
    private final RegionClient regionClient;

    @Override
    public List<String> getRegions() {

        return regionClient.getRegions(ovhConfig.getProjectId());
    }

    @Override
    public RegionApi getRegion(String regionName) {

        return regionClient.getRegion(ovhConfig.getProjectId(), regionName);
    }
}
