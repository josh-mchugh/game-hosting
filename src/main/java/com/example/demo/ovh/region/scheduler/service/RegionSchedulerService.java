package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.aggregate.command.RegionUpdateCommand;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.feign.IRegionFeignService;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
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

            RegionApi api = regionFeignService.getRegion(name);

            if(regionProjection.existsByName(name)) {

                FetchRegionByNameQuery query = new FetchRegionByNameQuery(name);
                Region region = regionProjection.fetchRegionByName(query);

                if(isDifferent(region, api)) {

                    builder.updatedRegion(handleUpdateRegion(region.getId(), api));
                }

            }else {

                builder.createdRegion(handleCreateRegion(api));
            }
        }

        return builder.build();
    }

    private Object handleUpdateRegion(String id, RegionApi region) {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .id(UUID.fromString(id))
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

    private boolean isDifferent(Region region, RegionApi api) {

        if (!StringUtils.equals(region.getCountryCodes(), joinCountryCodes(api.getIpCountries()))) return true;
        if (!StringUtils.equals(region.getContinentCode(), api.getContinentCode())) return true;
        if (!StringUtils.equals(region.getDataCenterLocation(), api.getDataCenterLocation())) return true;

        return !Objects.equals(region.getStatus(), api.getStatus());
    }
}
