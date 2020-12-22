package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.aggregate.command.ImageUpdateCommand;
import com.example.demo.ovh.image.feign.IImageFeignService;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.image.projection.model.ExistImageNameAndRegionNameQuery;
import com.example.demo.ovh.image.projection.model.FetchImageAndRegionIdProjection;
import com.example.demo.ovh.image.projection.model.FetchImageIdAndRegionIdQuery;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
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
public class ImageSchedulerService implements IImageSchedulerService {

    private final IImageFeignService imageFeignService;
    private final IImageProjector imageProjector;
    private final IRegionProjector regionProjector;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<ImageApi> getImageResponses() {

        return ImmutableList.copyOf(imageFeignService.getImages());
    }

    @Override
    public ProcessedImagesResponse processScheduledImages(ImmutableList<ImageApi> imageResponses) {

        ProcessedImagesResponse.Builder builder = ProcessedImagesResponse.builder();
        FetchRegionIdsGroupByNameProjection projection = regionProjector.fetchRegionIdsGroupedByName();

        for (ImageApi response : imageResponses) {

            ExistImageNameAndRegionNameQuery query = ExistImageNameAndRegionNameQuery.builder()
                    .name(response.getName())
                    .regionName(response.getRegionName())
                    .build();

            if(imageProjector.existsByNameAndRegionName(query)) {

                builder.updatedImage(processImageUpdate(response));

            } else {

                builder.createdImage(processImageCreate(response, projection.getRegionMap()));
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

        return ImageUpdateCommand.builder()
                .id(UUID.fromString(projection.getId()))
                .ovhId(imageResponse.getId())
                .regionId(projection.getRegionId())
                .name(imageResponse.getName())
                .type(imageResponse.getType())
                .imageCreatedDate(imageResponse.getCreationDate())
                .flavorType(imageResponse.getFlavorType())
                .hourly(imageResponse.getHourly())
                .monthly(imageResponse.getMonthly())
                .size(imageResponse.getSize())
                .minRam(imageResponse.getMinRam())
                .minDisk(imageResponse.getMinDisk())
                .username(imageResponse.getUser())
                .status(imageResponse.getStatus())
                .visibility(imageResponse.getVisibility())
                .build();
    }

    private Object processImageCreate(ImageApi imageResponse, ImmutableMap<String, String> regionMap) {

        ImageCreateCommand command = imageCreateCommand(imageResponse, regionMap);

        return commandGateway.sendAndWait(command);
    }

    private ImageCreateCommand imageCreateCommand(ImageApi response, ImmutableMap<String, String>regionMap) {

        return ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .ovhId(response.getId())
                .regionId(regionMap.get(response.getRegionName()))
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
