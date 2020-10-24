package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.aggregate.command.FlavorUpdateCommand;
import com.example.demo.ovh.flavor.feign.FlavorClient;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByFlavorIdQuery;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByFlavorIdResponse;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
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
public class FlavorSchedulerService implements IFlavorSchedulerService {

    private final OvhConfig ovhConfig;
    private final FlavorClient flavorClient;
    private final IFlavorProjector flavorProjectionService;
    private final IRegionProjection regionProjection;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<FlavorApi> getFlavorResponses() {

        return ImmutableList.copyOf(flavorClient.getFlavors(ovhConfig.getProjectId()));
    }

    @Override
    public ProcessedFlavorsResponse processFlavors(ImmutableList<FlavorApi> flavorResponses) {

        ProcessedFlavorsResponse.Builder builder = ProcessedFlavorsResponse.builder();

        for(FlavorApi flavorResponse : flavorResponses) {

            if (flavorProjectionService.existsByFlavorId(flavorResponse.getFlavorId())) {

                builder.updatedFlavor(handleFlavorUpdate(flavorResponse));

            } else {

                builder.createdFlavor(handleFlavorCreate(flavorResponse));
            }

        }

        return builder.build();
    }

    private Object handleFlavorUpdate(FlavorApi flavorResponse) {

        // TODO: Simplify, for loop above and the love of simplicity
        FetchRegionIdByNameQuery regionIdQuery = FetchRegionIdByNameQuery.builder()
                .name(flavorResponse.getRegionName())
                .build();

        FetchRegionIdByNameResponse regionIdResponse = regionProjection.fetchIdByName(regionIdQuery);

        FetchFlavorIdByFlavorIdQuery flavorIdQuery = FetchFlavorIdByFlavorIdQuery.builder()
                .flavorId(flavorResponse.getFlavorId())
                .build();

        FetchFlavorIdByFlavorIdResponse flavorIdResponse = flavorProjectionService.fetchFlavorIdByFlavorId(flavorIdQuery);

        String hourly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getHourly() : null;
        String monthly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getMonthly() : null;

        FlavorUpdateCommand request = FlavorUpdateCommand.builder()
                .id(UUID.fromString(flavorIdResponse.getId()))
                .regionId(regionIdResponse.getId())
                .name(flavorResponse.getName())
                .type(flavorResponse.getType())
                .available(flavorResponse.isAvailable())
                .hourly(hourly)
                .monthly(monthly)
                .osType(flavorResponse.getOsType())
                .quota(flavorResponse.getQuota())
                .vcpus(flavorResponse.getVcpus())
                .ram(flavorResponse.getRam())
                .disk(flavorResponse.getDisk())
                .inboundBandwidth(flavorResponse.getInboundBandwidth())
                .outboundBandwidth(flavorResponse.getOutboundBandwidth())
                .build();

        return commandGateway.sendAndWait(request);
    }

    private Object handleFlavorCreate(FlavorApi flavorResponse) {

        // TODO: Make more efficient for loop
        FetchRegionIdByNameQuery query = FetchRegionIdByNameQuery.builder()
                .name(flavorResponse.getRegionName())
                .build();

        FetchRegionIdByNameResponse response = regionProjection.fetchIdByName(query);

        String hourly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getHourly() : null;
        String monthly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getMonthly() : null;

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId(flavorResponse.getFlavorId())
                .regionId(response.getId())
                .name(flavorResponse.getName())
                .type(flavorResponse.getType())
                .available(flavorResponse.isAvailable())
                .hourly(hourly)
                .monthly(monthly)
                .osType(flavorResponse.getOsType())
                .quota(flavorResponse.getQuota())
                .vcpus(flavorResponse.getVcpus())
                .ram(flavorResponse.getRam())
                .disk(flavorResponse.getDisk())
                .inboundBandwidth(flavorResponse.getInboundBandwidth())
                .outboundBandwidth(flavorResponse.getOutboundBandwidth())
                .build();

        return commandGateway.sendAndWait(command);
    }
}
