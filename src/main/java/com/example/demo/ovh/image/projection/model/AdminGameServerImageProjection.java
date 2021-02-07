package com.example.demo.ovh.image.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AdminGameServerImageProjection {

    String id;
    String name;

    @QueryProjection
    public AdminGameServerImageProjection(String id, String name) {

        this.id = id;
        this.name = name;
    }
}
