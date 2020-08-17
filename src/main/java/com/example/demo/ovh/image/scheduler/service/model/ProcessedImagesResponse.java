package com.example.demo.ovh.image.scheduler.service.model;

import com.example.demo.ovh.image.model.Image;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProcessedImagesResponse {

    @Singular
    ImmutableList<Image> createdImages;

    @Singular
    ImmutableList<Image> updatedImages;
}
