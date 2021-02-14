package com.example.demo.framework.seed.service;

import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.feign.IImageFeignService;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.projection.IImageProjector;
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
public class ImageSeedService implements ISeedService<Object> {

    private final IImageProjector imageProjector;
    private final IImageFeignService imageFeignService;
    private final CommandGateway commandGateway;
    private final IRegionProjector regionProjector;

    @Override
    public boolean dataNotExists() {

        return !imageProjector.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        FetchRegionIdsGroupByNameProjection projection = regionProjector.fetchRegionIdsGroupedByName();

        return imageFeignService.getImages().stream()
                .map(response -> this.imageCreateCommand(response, projection.getRegionMap()))
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

    private ImageCreateCommand imageCreateCommand(ImageApi response, ImmutableMap<String, String> regionMap) {

        UUID regionId = UUID.fromString(regionMap.get(response.getRegionName()));

        return ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId(regionId)
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
