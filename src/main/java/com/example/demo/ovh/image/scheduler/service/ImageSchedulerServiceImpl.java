package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.aggregate.command.ImageUpdateCommand;
import com.example.demo.ovh.image.feign.ImageFeignService;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameResponse;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameResponse;
import com.example.demo.ovh.image.scheduler.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.image.scheduler.projection.projection.ImageProjection;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
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
public class ImageSchedulerServiceImpl implements ImageSchedulerService {

    private final ImageFeignService imageFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<ImageApi> getImageResponses() {

        return ImmutableList.copyOf(imageFeignService.getImages());
    }

    @Override
    public ProcessedImagesResponse processScheduledImages(ImmutableList<ImageApi> imageResponses) throws ExecutionException, InterruptedException {

        ProcessedImagesResponse.Builder builder = ProcessedImagesResponse.builder();
        ImmutableMap<String, String> regions = fetchRegionIdsGroupedByName();

        for (ImageApi api : imageResponses) {

            if(existsByNameAndRegionName(api.getName(), api.getRegionName())) {

                ImageProjection image = fetchImageByNameAndRegionName(api.getName(), api.getRegionName());

                if(isDifferent(image, api)) {

                    builder.updatedImage(processImageUpdate(image.getId(), api));
                }

            } else {

                builder.createdImage(processImageCreate(api, regions.get(api.getRegionName())));
            }
        }

        return builder.build();
    }

    private ImmutableMap<String, String> fetchRegionIdsGroupedByName() throws ExecutionException, InterruptedException {

        FetchRegionIdsGroupedByNameQuery query = new FetchRegionIdsGroupedByNameQuery();
        FetchRegionIdsGroupedByNameResponse response = queryGateway.query(query, FetchRegionIdsGroupedByNameResponse.class).get();

        return response.getRegions();
    }

    private boolean existsByNameAndRegionName(String name, String regionName) throws ExecutionException, InterruptedException {

        ExistsImageByNameAndRegionNameQuery query = new ExistsImageByNameAndRegionNameQuery(name, regionName);
        ExistsImageByNameAndRegionNameResponse response = queryGateway.query(query, ExistsImageByNameAndRegionNameResponse.class).get();

        return response.exists();
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

    private Object processImageCreate(ImageApi imageResponse, String regionId) {

        ImageCreateCommand command = imageCreateCommand(imageResponse, regionId);

        return commandGateway.sendAndWait(command);
    }

    private ImageCreateCommand imageCreateCommand(ImageApi response, String regionId) {

        return ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .ovhId(response.getId())
                .regionId(UUID.fromString(regionId))
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

    private ImageProjection fetchImageByNameAndRegionName(String name, String regionName) throws ExecutionException, InterruptedException {

        FetchImageProjectionByNameAndRegionNameQuery query = new FetchImageProjectionByNameAndRegionNameQuery(name, regionName);
        FetchImageProjectionByNameAndRegionNameResponse response = queryGateway.query(query, FetchImageProjectionByNameAndRegionNameResponse.class).get();

        return response.getImage();
    }

    private boolean isDifferent(ImageProjection image, ImageApi api) {

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
