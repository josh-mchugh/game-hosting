package com.example.demo.ovh.region.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchRegionIdByNameProjection {

    String id;

    @QueryProjection
    public FetchRegionIdByNameProjection(String id) {

        this.id = id;
    }
}
