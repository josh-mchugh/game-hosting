package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.feign.region.RegionClient;
import com.example.demo.ovh.feign.region.model.RegionApi;
import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.projection.IRegionProjection;
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
public class RegionSeedService implements ISeedService<Object> {

    private final OvhConfig ovhConfig;
    private final IRegionProjection regionProjection;
    private final RegionClient regionClient;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !regionProjection.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        return regionClient.getRegions(ovhConfig.getProjectId()).stream()
                .map(name -> regionClient.getRegion(ovhConfig.getProjectId(), name))
                .map(this::createCommand)
                .map(commandGateway::sendAndWait)
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

    private RegionCreateCommand createCommand(RegionApi regionApi) {

        return RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(regionApi.getName())
                .continentCode(regionApi.getContinentCode())
                .countryCodes(joinCountryCodes(regionApi.getIpCountries()))
                .dataCenterLocation(regionApi.getDataCenterLocation())
                .status(regionApi.getStatus())
                .build();
    }

    private String joinCountryCodes(List<String> countryCodes) {

        if(CollectionUtils.isNotEmpty(countryCodes)) {

            return Joiner.on(",").join(countryCodes);
        }

        return null;
    }
}
