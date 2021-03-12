package com.example.demo.ovh.image.scheduler.projection.model;

import com.example.demo.ovh.image.scheduler.projection.projection.ImageProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchImageProjectionByNameAndRegionNameResponse {

    ImageProjection image;
}
