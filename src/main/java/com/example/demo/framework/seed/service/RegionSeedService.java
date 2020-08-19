package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.feign.region.RegionClient;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.mapper.RegionCreateRequestMapper;
import com.google.common.collect.ImmutableList;
import com.google.j2objc.annotations.AutoreleasePool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionSeedService implements ISeedService<Region> {

    private final AppConfig appConfig;
    private final IRegionService regionService;
    private final RegionClient regionClient;

    @Override
    public boolean dataNotExists() {

        return !regionService.existsAny();
    }

    @Override
    public ImmutableList<Region> initializeData() {

        return regionClient.getRegions(appConfig.getOvh().getProjectId()).stream()
                .map(name -> regionClient.getRegion(appConfig.getOvh().getProjectId(), name))
                .map(RegionCreateRequestMapper::map)
                .map(regionService::handleRegionCreate)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "Region";
    }

    @Override
    public Integer order() {

        return 2;
    }
}
