package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.region.feign.RegionClient;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.aggregate.command.RegionUpdateCommand;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameResponse;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegionSchedulerService implements IRegionSchedulerService {

    private final OvhConfig ovhConfig;
    private final RegionClient regionClient;
    private final IRegionProjector regionProjection;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<String> getRegionNames() {

        return ImmutableList.copyOf(regionClient.getRegions(ovhConfig.getProjectId()));
    }

    @Override
    public ProcessRegionResponse processRegions(ImmutableList<String> regionNames) {

        ProcessRegionResponse.Builder builder = ProcessRegionResponse.builder();

        for(String name : regionNames) {

            RegionApi regionResponse = regionClient.getRegion(ovhConfig.getProjectId(), name);

            if(regionProjection.existsByName(name)) {

                builder.updatedRegion(handleUpdateRegion(regionResponse));

            }else {

                builder.createdRegion(handleCreateRegion(regionResponse));
            }
        }

        return builder.build();
    }

    private Object handleUpdateRegion(RegionApi region) {

        FetchRegionIdByNameQuery query = FetchRegionIdByNameQuery.builder()
                .name(region.getName())
                .build();

        FetchRegionIdByNameResponse response = regionProjection.fetchIdByName(query);

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .id(UUID.fromString(response.getId()))
                .continentCode(region.getContinentCode())
                .countryCodes(joinCountryCodes(region.getIpCountries()))
                .dataCenterLocation(region.getDataCenterLocation())
                .status(region.getStatus())
                .build();

        return commandGateway.sendAndWait(command);
    }

    private UUID handleCreateRegion(RegionApi region) {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(region.getName())
                .continentCode(region.getContinentCode())
                .countryCodes(joinCountryCodes(region.getIpCountries()))
                .dataCenterLocation(region.getDataCenterLocation())
                .status(region.getStatus())
                .build();

        return commandGateway.sendAndWait(command);
    }

    private String joinCountryCodes(List<String> countryCodes) {

        if(CollectionUtils.isNotEmpty(countryCodes)) {

            return Joiner.on(",").join(countryCodes);
        }

        return null;
    }
}
