package com.example.demo.user.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class FetchUserIdByEmailProjection {

    UUID id;

    @QueryProjection
    public FetchUserIdByEmailProjection(String id) {

        this.id = UUID.fromString(id);
    }
}
