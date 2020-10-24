package com.example.demo.ovh.flavor.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchFlavorIdByFlavorIdResponse {

    String id;

    @QueryProjection
    public FetchFlavorIdByFlavorIdResponse(String id) {

        this.id = id;
    }
}
