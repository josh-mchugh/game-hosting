package com.example.demo.ovh.image.scheduler.service.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder(builderClassName = "Builder")
public class ProcessedImagesResponse {

    @Singular
    List<Object> createdImages;

    @Singular
    List<Object> updatedImages;
}
