package com.example.demo.ovh.image.projection.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FetchImageIdAndRegionIdQuery {

    String imageName;
    String regionName;
}
