package com.example.demo.ovh.flavor.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchFlavorIdByOvhIdProjection {

    String id;

    @QueryProjection
    public FetchFlavorIdByOvhIdProjection(String id) {

        this.id = id;
    }
}
