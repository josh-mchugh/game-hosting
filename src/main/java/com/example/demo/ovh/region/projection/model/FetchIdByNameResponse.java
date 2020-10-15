package com.example.demo.ovh.region.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FetchIdByNameResponse {

    private final String id;

    @QueryProjection
    public FetchIdByNameResponse(String id) {

        this.id = id;
    }
}
