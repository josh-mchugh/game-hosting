package com.example.demo.awx.host.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AwxHostAwxIdProjection {

    String awxHostId;
    Long awxId;

    @QueryProjection
    public AwxHostAwxIdProjection(String awxHostId, Long awxId) {

        this.awxHostId = awxHostId;
        this.awxId = awxId;
    }
}
