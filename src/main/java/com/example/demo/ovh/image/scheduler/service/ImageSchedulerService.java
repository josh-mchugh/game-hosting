package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.feign.model.OvhImageApiResponse;
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

    private final AppConfig appConfig;
    private final OvhClient ovhClient;
    private final IImageService imageService;

    @Override
    public ImmutableList<OvhImageApiResponse> getImageResponses() {

        return ImmutableList.copyOf(ovhClient.getImages(appConfig.getOvh().getProjectId()));
    }

    @Override
    public ProcessedImagesResponse processScheduledImages(ImmutableList<OvhImageApiResponse> imageResponses) {

        ProcessedImagesResponse.Builder builder = ProcessedImagesResponse.builder();

        for (OvhImageApiResponse response : imageResponses) {

            if(imageService.existsByImageId(response.getImageId())) {

                builder.updatedImage(processImageUpdate(response));

            } else {

                builder.createdImage(processImageCreate(response));
            }
        }

        return builder.build();
    }

    private Image processImageUpdate(OvhImageApiResponse imageResponse) {

        ImageUpdateRequest imageUpdateRequest = buildImageUpdateRequest(imageResponse);

        return imageService.handleImageUpdate(imageUpdateRequest);
    }

    private ImageUpdateRequest buildImageUpdateRequest(OvhImageApiResponse imageResponse) {

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

    private Image processImageCreate(OvhImageApiResponse imageResponse) {

        ImageCreateRequest imageCreateRequest = buildImageCreateRequest(imageResponse);

        return imageService.handleImageCreate(imageCreateRequest);
    }

    private ImageCreateRequest buildImageCreateRequest(OvhImageApiResponse imageResponse) {

        String hourly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getHourly() : null;
        String monthly = imageResponse.getPlanCode() != null ? imageResponse.getPlanCode().getMonthly() : null;
        ImmutableList<String> tags = CollectionUtils.isNotEmpty(imageResponse.getTags()) ? ImmutableList.copyOf(imageResponse.getTags()) : null;

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
                .tags(tags)
                .build();
    }
}
