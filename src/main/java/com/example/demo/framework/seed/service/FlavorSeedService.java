package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.feign.FlavorClient;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.region.projection.IRegionProjection;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FlavorSeedService implements ISeedService<Object> {

    private final OvhConfig ovhConfig;
    private final IFlavorProjector flavorProjectionService;
    private final FlavorClient flavorClient;
    private final IRegionProjection regionProjection;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !flavorProjectionService.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        return flavorClient.getFlavors(ovhConfig.getProjectId()).stream()
                .map(this::buildFlavorCreateCommand)
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

    private FlavorCreateCommand buildFlavorCreateCommand(FlavorApi flavor) {

        // TODO: Make more efficient for loop
        FetchRegionIdByNameQuery query = FetchRegionIdByNameQuery.builder()
                .name(flavor.getRegionName())
                .build();

        FetchRegionIdByNameResponse response = regionProjection.fetchIdByName(query);

        return FlavorCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId(response.getId())
                .flavorId(flavor.getFlavorId())
                .name(flavor.getName())
                .type(flavor.getType())
                .hourly(flavor.getPlanCodes() != null ? flavor.getPlanCodes().getHourly() : null)
                .monthly(flavor.getPlanCodes() != null ? flavor.getPlanCodes().getMonthly() : null)
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
