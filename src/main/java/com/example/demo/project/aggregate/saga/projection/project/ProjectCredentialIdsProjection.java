package com.example.demo.project.aggregate.saga.projection.project;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class ProjectCredentialIdsProjection {

    UUID id;
    String ovhId;

    @QueryProjection
    public ProjectCredentialIdsProjection(String id, String ovhId) {
        this.id = UUID.fromString(id);
        this.ovhId = ovhId;
    }
}
