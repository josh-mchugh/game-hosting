package com.example.demo.awx.organization.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchAwxOrganizationIdByAwxIdResponse {

    String id;

    @QueryProjection
    public FetchAwxOrganizationIdByAwxIdResponse(String id) {

        this.id = id;
    }
}
