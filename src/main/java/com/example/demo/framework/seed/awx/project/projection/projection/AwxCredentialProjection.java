package com.example.demo.framework.seed.awx.project.projection.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AwxCredentialProjection {

    String id;
    Long awxId;

    @QueryProjection
    public AwxCredentialProjection(String id, Long awxId) {
        this.id = id;
        this.awxId = awxId;
    }
}
