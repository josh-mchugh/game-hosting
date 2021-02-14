package com.example.demo.user.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class FetchUserIdByPasswordResetTokenProjection {

    UUID id;

    @QueryProjection
    public FetchUserIdByPasswordResetTokenProjection(String id) {

        this.id = UUID.fromString(id);
    }
}
