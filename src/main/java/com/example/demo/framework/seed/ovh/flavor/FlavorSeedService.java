package com.example.demo.framework.seed.ovh.flavor;

import com.example.demo.framework.seed.ISeedService;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorResponse;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.feign.IFlavorFeignService;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class FlavorSeedService implements ISeedService<Object> {

    private final IFlavorFeignService flavorFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyFlavorQuery query = new ExistsAnyFlavorQuery();
        ExistsAnyFlavorResponse response = queryGateway.query(query, ExistsAnyFlavorResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() throws ExecutionException, InterruptedException {

       ImmutableMap<String, String> regions = getRegions();

        return flavorFeignService.getFlavors().stream()
                .map(response -> this.buildFlavorCreateCommand(response, regions.get(response.getRegionName())))
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

    private ImmutableMap<String, String> getRegions() throws ExecutionException, InterruptedException {

        FetchRegionIdsGroupedByNameQuery query = new FetchRegionIdsGroupedByNameQuery();
        FetchRegionIdsGroupedByNameResponse response = queryGateway.query(query, FetchRegionIdsGroupedByNameResponse.class).get();

        return response.getRegions();
    }

    private FlavorCreateCommand buildFlavorCreateCommand(FlavorApi flavor, String regionId) {

        return FlavorCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId(UUID.fromString(regionId))
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
