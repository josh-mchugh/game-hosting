package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.aggregate.command.RegionUpdateCommand;
import com.example.demo.ovh.region.feign.RegionFeignService;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameResponse;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameResponse;
import com.example.demo.ovh.region.scheduler.projection.projection.RegionProjection;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class RegionSchedulerServiceImpl implements RegionSchedulerService {

    private final RegionFeignService regionFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<String> getRegionNames() {

        return ImmutableList.copyOf(regionFeignService.getRegions());
    }

    @Override
    public ProcessRegionResponse processRegions(ImmutableList<String> regionNames) throws ExecutionException, InterruptedException {

        ProcessRegionResponse.Builder builder = ProcessRegionResponse.builder();

        for(String name : regionNames) {

            RegionApi api = regionFeignService.getRegion(name);

            if(existsByName(name)) {

                RegionProjection region = fetchRegionByName(name);

                if(isDifferent(region, api)) {

                    builder.updatedRegion(handleUpdateRegion(region.getId(), api));
                }

            }else {

                builder.createdRegion(handleCreateRegion(api));
            }
        }

        return builder.build();
    }

    private boolean existsByName(String name) throws ExecutionException, InterruptedException {

        ExistsRegionByNameQuery query = new ExistsRegionByNameQuery(name);
        ExistsRegionByNameResponse response = queryGateway.query(query, ExistsRegionByNameResponse.class).get();

        return response.exists();
    }

    private RegionProjection fetchRegionByName(String name) throws ExecutionException, InterruptedException {

        FetchRegionByNameQuery query = new FetchRegionByNameQuery(name);
        FetchRegionByNameResponse response = queryGateway.query(query, FetchRegionByNameResponse.class).get();

        return response.getRegion();
    }

    private Object handleUpdateRegion(UUID id, RegionApi region) {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .id(id)
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

    private boolean isDifferent(RegionProjection region, RegionApi api) {

        if (!StringUtils.equals(region.getCountryCodes(), joinCountryCodes(api.getIpCountries()))) return true;
        if (!StringUtils.equals(region.getContinentCode(), api.getContinentCode())) return true;
        if (!StringUtils.equals(region.getDataCenterLocation(), api.getDataCenterLocation())) return true;

        return !Objects.equals(region.getStatus(), api.getStatus());
    }
}
