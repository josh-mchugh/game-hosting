package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.aggregate.command.ImageUpdateCommand;
import com.example.demo.ovh.image.feign.ImageClient;
import com.example.demo.ovh.instance.feign.model.ImageApi;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.image.projection.model.ExistImageNameAndRegionNameQuery;
import com.example.demo.ovh.image.projection.model.FetchImageAndRegionIdProjection;
import com.example.demo.ovh.image.projection.model.FetchImageIdAndRegionIdQuery;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
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
public class ImageSchedulerService implements IImageSchedulerService {

    private final OvhConfig ovhConfig;
    private final ImageClient imageClient;
    private final IImageProjector imageProjector;
    private final IRegionProjector regionProjector;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<ImageApi> getImageResponses() {

        return ImmutableList.copyOf(imageClient.getImages(ovhConfig.getProjectId()));
    }

    @Override
    public ProcessedImagesResponse processScheduledImages(ImmutableList<ImageApi> imageResponses) {

        ProcessedImagesResponse.Builder builder = ProcessedImagesResponse.builder();

        for (ImageApi response : imageResponses) {

            ExistImageNameAndRegionNameQuery query = ExistImageNameAndRegionNameQuery.builder()
                    .name(response.getName())
                    .regionName(response.getRegionName())
                    .build();

            if(imageProjector.existsByNameAndRegionName(query)) {

                builder.updatedImage(processImageUpdate(response));

            } else {

                builder.createdImage(processImageCreate(response));
            }
        }

        return builder.build();
    }

    private Object processImageUpdate(ImageApi imageResponse) {

        ImageUpdateCommand command = imageUpdateCommand(imageResponse);

        return commandGateway.sendAndWait(command);
    }

    private ImageUpdateCommand imageUpdateCommand(ImageApi imageResponse) {

        FetchImageIdAndRegionIdQuery query = FetchImageIdAndRegionIdQuery.builder()
                .imageName(imageResponse.getName())
                .regionName(imageResponse.getRegionName())
                .build();
        FetchImageAndRegionIdProjection projection = imageProjector.fetchImageIdAndRegionIdQuery(query);

        String hourly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getHourly() : null;
        String monthly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getMonthly() : null;

        return ImageUpdateCommand.builder()
                .id(UUID.fromString(projection.getId()))
                .imageId(imageResponse.getImageId())
                .regionId(projection.getRegionId())
                .name(imageResponse.getName())
                .type(imageResponse.getType())
                .imageCreatedDate(imageResponse.getCreationDate())
                .flavorType(imageResponse.getFlavorType())
                .hourly(hourly)
                .monthly(monthly)
                .size(imageResponse.getSize())
                .minRam(imageResponse.getMinRam())
                .minDisk(imageResponse.getMinDisk())
                .username(imageResponse.getUser())
                .status(imageResponse.getStatus())
                .visibility(imageResponse.getVisibility())
                .build();
    }

    private Object processImageCreate(ImageApi imageResponse) {

        ImageCreateCommand command = imageCreateCommand(imageResponse);

        return commandGateway.sendAndWait(command);
    }

    private ImageCreateCommand imageCreateCommand(ImageApi response) {

        //TODO: Replace to prevent excess call in loop
        FetchRegionIdByNameQuery query = FetchRegionIdByNameQuery.builder()
                .name(response.getRegionName())
                .build();
        FetchRegionIdByNameResponse regionIdByNameResponse = regionProjector.fetchIdByName(query);

        String hourly = response.getPlanCode() != null ? response.getPlanCode().getHourly() : null;
        String monthly = response.getPlanCode() != null ? response.getPlanCode().getMonthly() : null;

        return ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .imageId(response.getImageId())
                .regionId(regionIdByNameResponse.getId())
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
