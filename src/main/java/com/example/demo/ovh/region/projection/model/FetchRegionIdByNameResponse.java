package com.example.demo.ovh.region.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchRegionIdByNameResponse {

    String id;

    @QueryProjection
    public FetchRegionIdByNameResponse(String id) {

        this.id = id;
    }
}
