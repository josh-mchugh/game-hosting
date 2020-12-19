package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.aggregate.command.FlavorUpdateCommand;
import com.example.demo.ovh.flavor.feign.IFlavorFeignService;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdQuery;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdProjection;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.example.demo.ovh.region.projection.IRegionProjector;
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

    private final IFlavorFeignService flavorFeignService;
    private final IFlavorProjector flavorProjectionService;
    private final IRegionProjector regionProjection;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<FlavorApi> getFlavorResponses() {

        return ImmutableList.copyOf(flavorFeignService.getFlavors());
    }

    @Override
    public ProcessedFlavorsResponse processFlavors(ImmutableList<FlavorApi> flavorResponses) {

        ProcessedFlavorsResponse.Builder builder = ProcessedFlavorsResponse.builder();

        for(FlavorApi flavorResponse : flavorResponses) {

            if (flavorProjectionService.existsByOvhId(flavorResponse.getId())) {

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

        FetchFlavorIdByOvhIdQuery flavorIdQuery = new FetchFlavorIdByOvhIdQuery(flavorResponse.getId());
        FetchFlavorIdByOvhIdProjection flavorIdResponse = flavorProjectionService.fetchFlavorIdByOvhId(flavorIdQuery);

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .id(UUID.fromString(flavorIdResponse.getId()))
                .regionId(regionIdResponse.getId())
                .name(flavorResponse.getName())
                .type(flavorResponse.getType())
                .available(flavorResponse.isAvailable())
                .hourly(flavorResponse.getHourly())
                .monthly(flavorResponse.getMonthly())
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

    private Object handleFlavorCreate(FlavorApi flavorResponse) {

        // TODO: Make more efficient for loop
        FetchRegionIdByNameQuery query = FetchRegionIdByNameQuery.builder()
                .name(flavorResponse.getRegionName())
                .build();

        FetchRegionIdByNameResponse response = regionProjection.fetchIdByName(query);

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .id(UUID.randomUUID())
                .ovhId(flavorResponse.getId())
                .regionId(response.getId())
                .name(flavorResponse.getName())
                .type(flavorResponse.getType())
                .available(flavorResponse.isAvailable())
                .hourly(flavorResponse.getHourly())
                .monthly(flavorResponse.getMonthly())
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
