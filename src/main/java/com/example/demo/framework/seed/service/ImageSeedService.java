package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.feign.image.ImageClient;
import com.example.demo.ovh.feign.image.model.ImageApi;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageSeedService implements ISeedService<Image> {

    private final OvhConfig ovhConfig;
    private final IImageService imageService;
    private final ImageClient imageClient;

    @Override
    public boolean dataNotExists() {

        return !imageService.existsAny();
    }

    @Override
    public ImmutableList<Image> initializeData() {

        return imageClient.getImages(ovhConfig.getProjectId()).stream()
                .map(this::buildImageCreateRequest)
                .map(imageService::handleImageCreate)
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

    private ImageCreateRequest buildImageCreateRequest(ImageApi response) {

        String hourly = response.getPlanCode() != null ? response.getPlanCode().getHourly() : null;
        String monthly = response.getPlanCode() != null ? response.getPlanCode().getHourly() : null;

        return ImageCreateRequest.builder()
                .imageId(response.getImageId())
                .regionName(response.getRegionName())
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
