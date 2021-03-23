package com.example.demo.framework.seed.awx.project.projection.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class AwxCredentialProjection {

    UUID id;
    Long awxId;

    @QueryProjection
    public AwxCredentialProjection(String id, Long awxId) {
        this.id = UUID.fromString(id);
        this.awxId = awxId;
    }
}
