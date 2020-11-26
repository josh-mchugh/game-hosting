package com.example.demo.user.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchUserIdByVerificationTokenProjection {

    String id;

    @QueryProjection
    public FetchUserIdByVerificationTokenProjection(String id) {

        this.id = id;
    }
}
