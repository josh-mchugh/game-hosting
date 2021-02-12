package com.example.demo.awx.organization.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class FetchAwxOrganizationIdByAwxIdResponse {

    String id;

    @QueryProjection
    public FetchAwxOrganizationIdByAwxIdResponse(String id) {

        this.id = id;
    }

    public UUID getId() {

        return UUID.fromString(id);
    }
}
