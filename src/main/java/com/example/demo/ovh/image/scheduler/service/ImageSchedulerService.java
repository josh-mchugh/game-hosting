package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.aggregate.command.ImageUpdateCommand;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.feign.IImageFeignService;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.image.projection.model.ExistByNameAndRegionNameQuery;
import com.example.demo.ovh.image.projection.model.FetchImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.Objects;
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

        for (ImageApi api : imageResponses) {

            if(imageProjector.existsByNameAndRegionName(existsByNameAndRegionNameQuery(api))) {

                Image image = fetchImageByNameAndRegionName(api);

                if(isDifferent(image, api)) {

                    builder.updatedImage(processImageUpdate(image.getId(), api));
                }

            } else {

                builder.createdImage(processImageCreate(api, projection.getRegionMap()));
            }
        }

        return builder.build();
    }

    private Object processImageUpdate(UUID id, ImageApi imageResponse) {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId(imageResponse.getId())
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

        return commandGateway.sendAndWait(command);
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

    private ExistByNameAndRegionNameQuery existsByNameAndRegionNameQuery(ImageApi api) {

        return ExistByNameAndRegionNameQuery.builder()
                .name(api.getName())
                .regionName(api.getRegionName())
                .build();
    }

    private Image fetchImageByNameAndRegionName(ImageApi api) {

        FetchImageByNameAndRegionNameQuery query = FetchImageByNameAndRegionNameQuery.builder()
                .name(api.getName())
                .regionName(api.getRegionName())
                .build();

        return imageProjector.fetchImageByNameAndRegionName(query);
    }

    private boolean isDifferent(Image image, ImageApi api) {

        if (!StringUtils.equals(image.getOvhId(), api.getId())) return true;
        if (!StringUtils.equals(image.getType(), api.getType())) return true;
        if (!Objects.equals(image.getImageCreatedDate(), api.getCreationDate())) return true;
        if (!StringUtils.equals(image.getFlavorType(), api.getFlavorType())) return true;
        if (!StringUtils.equals(image.getHourly(), api.getHourly())) return true;
        if (!StringUtils.equals(image.getMonthly(), api.getMonthly())) return true;
        if (!Objects.equals(image.getSize(), api.getSize())) return true;
        if (!Objects.equals(image.getMinRam(), api.getMinRam())) return true;
        if (!Objects.equals(image.getMinDisk(), api.getMinDisk())) return true;
        if (!StringUtils.equals(image.getUsername(), api.getUser())) return true;
        if (!StringUtils.equals(image.getStatus(), api.getStatus())) return true;

        return !StringUtils.equals(image.getVisibility(), api.getVisibility());
    }
}
