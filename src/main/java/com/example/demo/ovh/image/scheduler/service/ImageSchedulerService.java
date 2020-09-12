package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.feign.image.ImageClient;
import com.example.demo.ovh.feign.image.model.ImageApi;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.image.service.model.ImageUpdateRequest;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageSchedulerService implements IImageSchedulerService {

    private final OvhConfig ovhConfig;
    private final ImageClient imageClient;
    private final IImageService imageService;

    @Override
    public ImmutableList<ImageApi> getImageResponses() {

        return ImmutableList.copyOf(imageClient.getImages(ovhConfig.getProjectId()));
    }

    @Override
    public ProcessedImagesResponse processScheduledImages(ImmutableList<ImageApi> imageResponses) {

        ProcessedImagesResponse.Builder builder = ProcessedImagesResponse.builder();

        for (ImageApi response : imageResponses) {

            if(imageService.existsByName(response.getName())) {

                builder.updatedImage(processImageUpdate(response));

            } else {

                builder.createdImage(processImageCreate(response));
            }
        }

        return builder.build();
    }

    private Image processImageUpdate(ImageApi imageResponse) {

        ImageUpdateRequest imageUpdateRequest = buildImageUpdateRequest(imageResponse);

        return imageService.handleImageUpdate(imageUpdateRequest);
    }

    private ImageUpdateRequest buildImageUpdateRequest(ImageApi imageResponse) {

        String hourly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getHourly() : null;
        String monthly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getMonthly() : null;
        ImmutableList<String> tags = CollectionUtils.isNotEmpty(imageResponse.getTags()) ? ImmutableList.copyOf(imageResponse.getTags()) : null;

        return ImageUpdateRequest.builder()
                .imageId(imageResponse.getImageId())
                .regionName(imageResponse.getRegionName())
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
                .tags(tags)
                .build();
    }

    private Image processImageCreate(ImageApi imageResponse) {

        ImageCreateRequest imageCreateRequest = buildImageCreateRequest(imageResponse);

        return imageService.handleImageCreate(imageCreateRequest);
    }

    private ImageCreateRequest buildImageCreateRequest(ImageApi imageResponse) {

        String hourly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getHourly() : null;
        String monthly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getMonthly() : null;

        return ImageCreateRequest.builder()
                .imageId(imageResponse.getImageId())
                .regionName(imageResponse.getRegionName())
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
}
