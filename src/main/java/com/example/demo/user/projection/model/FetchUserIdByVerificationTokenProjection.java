package com.example.demo.user.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class FetchUserIdByVerificationTokenProjection {

    UUID id;

    @QueryProjection
    public FetchUserIdByVerificationTokenProjection(String id) {

        this.id = UUID.fromString(id);
    }
}
