package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.aggregate.command.FlavorUpdateCommand;
import com.example.demo.ovh.flavor.feign.IFlavorFeignService;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdResponse;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdResponse;
import com.example.demo.ovh.flavor.scheduler.projection.projection.FlavorProjection;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class FlavorSchedulerService implements IFlavorSchedulerService {

    private final IFlavorFeignService flavorFeignService;
    private final IRegionProjector regionProjection;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<FlavorApi> getFlavorResponses() {

        return ImmutableList.copyOf(flavorFeignService.getFlavors());
    }

    @Override
    public ProcessedFlavorsResponse processFlavors(ImmutableList<FlavorApi> flavorResponses) throws ExecutionException, InterruptedException {

        ProcessedFlavorsResponse.Builder builder = ProcessedFlavorsResponse.builder();
        FetchRegionIdsGroupByNameProjection projection = regionProjection.fetchRegionIdsGroupedByName();

        for(FlavorApi flavorResponse : flavorResponses) {

            if (existsByOvhId(flavorResponse.getId())) {

                FlavorProjection flavor = getFlavorByOvhId(flavorResponse.getId());

                if(isDifferent(flavor, flavorResponse)) {

                    builder.updatedFlavor(handleFlavorUpdate(flavor.getId(), flavorResponse));
                }

            } else {

                builder.createdFlavor(handleFlavorCreate(flavorResponse, projection.getRegionMap()));
            }

        }

        return builder.build();
    }

    private boolean existsByOvhId(String ovhId) throws ExecutionException, InterruptedException {

        ExistsFlavorByOvhIdQuery query = new ExistsFlavorByOvhIdQuery(ovhId);
        ExistsFlavorByOvhIdResponse response = queryGateway.query(query, ExistsFlavorByOvhIdResponse.class).get();

        return response.exists();
    }

    private FlavorProjection getFlavorByOvhId(String ovhId) throws ExecutionException, InterruptedException {

        FetchFlavorByOvhIdQuery query = new FetchFlavorByOvhIdQuery(ovhId);
        FetchFlavorByOvhIdResponse response = queryGateway.query(query, FetchFlavorByOvhIdResponse.class).get();

        return response.getFlavor();
    }

    private Object handleFlavorUpdate(UUID id, FlavorApi flavorResponse) {

        FlavorUpdateCommand command = FlavorUpdateCommand.builder()
                .id(id)
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

    private Object handleFlavorCreate(FlavorApi flavorResponse, ImmutableMap<String, String> regionsMap) {

        UUID regionId = UUID.fromString(regionsMap.get(flavorResponse.getRegionName()));

        FlavorCreateCommand command = FlavorCreateCommand.builder()
                .id(UUID.randomUUID())
                .ovhId(flavorResponse.getId())
                .regionId(regionId)
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

    private boolean isDifferent(FlavorProjection flavor, FlavorApi flavorApi) {

        if (!StringUtils.equals(flavor.getName(), flavorApi.getName())) return true;
        if (!StringUtils.equals(flavor.getType(), flavorApi.getType())) return true;
        if (!Objects.equals(flavor.getAvailable(), flavorApi.isAvailable())) return true;
        if (!StringUtils.equals(flavor.getHourly(), flavorApi.getHourly())) return true;
        if (!StringUtils.equals(flavor.getMonthly(), flavorApi.getMonthly())) return true;
        if (!StringUtils.equals(flavor.getOsType(), flavorApi.getOsType())) return true;
        if (!Objects.equals(flavor.getQuota(), flavorApi.getQuota())) return true;
        if (!Objects.equals(flavor.getVcpus(), flavorApi.getVcpus())) return true;
        if (!Objects.equals(flavor.getRam(), flavorApi.getRam())) return true;
        if (!Objects.equals(flavor.getDisk(), flavorApi.getDisk())) return true;
        if (!Objects.equals(flavor.getInboundBandwidth(), flavorApi.getInboundBandwidth())) return true;

        return !Objects.equals(flavor.getOutboundBandwidth(), flavorApi.getOutboundBandwidth());
    }
}
