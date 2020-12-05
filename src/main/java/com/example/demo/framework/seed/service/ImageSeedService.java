package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.feign.ImageClient;
import com.example.demo.ovh.instance.feign.model.ImageApi;
import com.example.demo.ovh.image.entity.service.IImageService;
import com.example.demo.ovh.image.projection.IImageProjector;
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
public class ImageSeedService implements ISeedService<Object> {

    private final OvhConfig ovhConfig;
    private final IImageProjector imageProjector;
    private final IImageService imageService;
    private final ImageClient imageClient;
    private final CommandGateway commandGateway;
    private final IRegionProjector regionProjector;

    @Override
    public boolean dataNotExists() {

        return !imageProjector.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        return imageClient.getImages(ovhConfig.getProjectId()).stream()
                .map(this::imageCreateCommand)
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

    private ImageCreateCommand imageCreateCommand(ImageApi response) {

        //TODO: Replace to prevent excess call in loop
        FetchRegionIdByNameQuery query = FetchRegionIdByNameQuery.builder()
                .name(response.getRegionName())
                .build();
        FetchRegionIdByNameResponse regionIdByNameResponse = regionProjector.fetchIdByName(query);

        String hourly = response.getPlanCode() != null ? response.getPlanCode().getHourly() : null;
        String monthly = response.getPlanCode() != null ? response.getPlanCode().getHourly() : null;

        return ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId(regionIdByNameResponse.getId())
                .imageId(response.getImageId())
                .name(response.getName())
                .type(response.getType())
                .imageCreatedDate(response.getCreationDate())
                .flavorType(response.getFlavorType())
                .hourly(hourly)
                .monthly(monthly)
                .size(response.getSize())
                .minRam(response.getMinRam())
                .minDisk(response.getMinDisk())
                .username(response.getUser())
                .status(response.getStatus())
                .visibility(response.getVisibility())
                .build();
    }
}
