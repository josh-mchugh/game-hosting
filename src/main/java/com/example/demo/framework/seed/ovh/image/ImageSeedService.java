package com.example.demo.framework.seed.ovh.image;

import com.example.demo.framework.seed.SeedService;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageResponse;
import com.example.demo.framework.seed.ovh.image.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.feign.ImageFeignService;
import com.example.demo.ovh.image.feign.model.ImageApi;
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
public class ImageSeedService implements SeedService<Object> {

    private final ImageFeignService imageFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyImageQuery query = new ExistsAnyImageQuery();
        ExistsAnyImageResponse response = queryGateway.query(query, ExistsAnyImageResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() throws ExecutionException, InterruptedException {

        ImmutableMap<String, String> regions = fetchRegionIdsGroupedByName();

        return imageFeignService.getImages().stream()
                .map(response -> this.imageCreateCommand(response, regions.get(response.getRegionName())))
                .map(commandGateway::sendAndWait)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "Image";
    }

    @Override
    public Integer order() {

        return 4;
    }

    private ImmutableMap<String, String> fetchRegionIdsGroupedByName() throws ExecutionException, InterruptedException {

        FetchRegionIdsGroupedByNameQuery query = new FetchRegionIdsGroupedByNameQuery();
        FetchRegionIdsGroupedByNameResponse response = queryGateway.query(query, FetchRegionIdsGroupedByNameResponse.class).get();

        return response.getRegions();
    }

    private ImageCreateCommand imageCreateCommand(ImageApi response, String regionId) {

        return ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId(UUID.fromString(regionId))
                .ovhId(response.getId())
                .name(response.getName())
                .type(response.getType())
                .imageCreatedDate(response.getCreationDate())
                .flavorType(response.getFlavorType())
                .hourly(response.getHourly())
                .monthly(response.getMonthly())
                .size(response.getSize())
                .minRam(response.getMinRam())
                .minDisk(response.getMinDisk())
                .username(response.getUser())
                .status(response.getStatus())
                .visibility(response.getVisibility())
                .build();
    }
}
