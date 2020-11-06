package com.example.demo.ovh.image.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchImageAndRegionIdProjection {

    String id;
    String regionId;

    @QueryProjection
    public FetchImageAndRegionIdProjection(String id, String regionId) {

        this.id = id;
        this.regionId = regionId;
    }
}
