package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.feign.model.OvhRegionApiResponse;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.mapper.RegionCreateRequestMapper;
import com.example.demo.ovh.region.service.mapper.RegionUpdateRequestMapper;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionSchedulerService implements IRegionSchedulerService {

    private final AppConfig appConfig;
    private final OvhClient ovhClient;
    private final IRegionService regionService;

    @Override
    public ImmutableList<String> getRegionNames() {

        return ImmutableList.copyOf(ovhClient.getRegions(appConfig.getOvh().getProjectId()));
    }

    @Override
    public ProcessRegionResponse processRegions(ImmutableList<String> regionNames) {

        ProcessRegionResponse.Builder builder = ProcessRegionResponse.builder();

        for(String name : regionNames) {

            OvhRegionApiResponse regionResponse = ovhClient.getRegion(appConfig.getOvh().getProjectId(), name);

            if(regionService.existsByName(name)) {

                builder.updatedRegion(handleUpdateRegion(regionResponse));

            }else {

                builder.createdRegion(handleCreateRegion(regionResponse));
            }
        }

        return builder.build();
    }

    private Region handleUpdateRegion(OvhRegionApiResponse region) {

        return regionService.handleRegionUpdate(RegionUpdateRequestMapper.map(region));
    }

    private Region handleCreateRegion(OvhRegionApiResponse region) {

        return regionService.handleRegionCreate(RegionCreateRequestMapper.map(region));
    }
}
