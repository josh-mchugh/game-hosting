package com.example.demo.framework.seed.service;

import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.feign.IFlavorFeignService;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FlavorSeedService implements ISeedService<Object> {

    private final IFlavorFeignService flavorFeignService;
    private final IFlavorProjector flavorProjectionService;
    private final IRegionProjector regionProjector;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !flavorProjectionService.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

       FetchRegionIdsGroupByNameProjection projection = regionProjector.fetchRegionIdsGroupedByName();

        return flavorFeignService.getFlavors().stream()
                .map(response -> this.buildFlavorCreateCommand(response, projection.getRegionMap()))
                .map(commandGateway::sendAndWait)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "Flavor";
    }

    @Override
    public Integer order() {

        return 3;
    }

    private FlavorCreateCommand buildFlavorCreateCommand(FlavorApi flavor, ImmutableMap<String, String> regionMap) {

        UUID regionId = UUID.fromString(regionMap.get(flavor.getRegionName()));

        return FlavorCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId(regionId)
                .ovhId(flavor.getId())
                .name(flavor.getName())
                .type(flavor.getType())
                .hourly(flavor.getHourly())
                .monthly(flavor.getMonthly())
                .osType(flavor.getOsType())
                .quota(flavor.getQuota())
                .available(flavor.isAvailable())
                .vcpus(flavor.getVcpus())
                .ram(flavor.getRam())
                .disk(flavor.getDisk())
                .inboundBandwidth(flavor.getInboundBandwidth())
                .outboundBandwidth(flavor.getOutboundBandwidth())
                .build();
    }
}
