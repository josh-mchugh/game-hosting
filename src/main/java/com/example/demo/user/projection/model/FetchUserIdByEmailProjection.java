package com.example.demo.user.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchUserIdByEmailProjection {

    String id;

    @QueryProjection
    public FetchUserIdByEmailProjection(String id) {

        this.id = id;
    }
}
