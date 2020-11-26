package com.example.demo.user.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchUserIdByPasswordResetTokenProjection {

    String id;

    @QueryProjection
    public FetchUserIdByPasswordResetTokenProjection(String id) {

        this.id = id;
    }
}
