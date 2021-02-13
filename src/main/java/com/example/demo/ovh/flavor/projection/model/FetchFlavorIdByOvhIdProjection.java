package com.example.demo.ovh.flavor.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class FetchFlavorIdByOvhIdProjection {

    UUID id;

    @QueryProjection
    public FetchFlavorIdByOvhIdProjection(String id) {

        this.id = UUID.fromString(id);
    }
}
