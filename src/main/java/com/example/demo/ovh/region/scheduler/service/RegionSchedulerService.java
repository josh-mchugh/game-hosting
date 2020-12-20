package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.aggregate.command.RegionUpdateCommand;
import com.example.demo.ovh.region.feign.IRegionFeignService;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameProjection;
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

    private final IRegionFeignService regionFeignService;
    private final IRegionProjector regionProjection;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<String> getRegionNames() {

        return ImmutableList.copyOf(regionFeignService.getRegions());
    }

    @Override
    public ProcessRegionResponse processRegions(ImmutableList<String> regionNames) {

        ProcessRegionResponse.Builder builder = ProcessRegionResponse.builder();

        for(String name : regionNames) {

            RegionApi regionResponse = regionFeignService.getRegion(name);

            if(regionProjection.existsByName(name)) {

                builder.updatedRegion(handleUpdateRegion(regionResponse));

            }else {

                builder.createdRegion(handleCreateRegion(regionResponse));
            }
        }

        return builder.build();
    }

    private Object handleUpdateRegion(RegionApi region) {

        FetchRegionIdByNameQuery query = new FetchRegionIdByNameQuery(region.getName());
        FetchRegionIdByNameProjection response = regionProjection.fetchIdByName(query);

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
