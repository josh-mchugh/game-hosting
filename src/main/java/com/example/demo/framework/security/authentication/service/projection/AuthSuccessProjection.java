package com.example.demo.framework.security.authentication.service.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class AuthSuccessProjection {

    UUID id;
    boolean isAdmin;

    @QueryProjection
    public AuthSuccessProjection(String id, boolean isAdmin) {

        this.id = UUID.fromString(id);
        this.isAdmin = isAdmin;
    }
}
