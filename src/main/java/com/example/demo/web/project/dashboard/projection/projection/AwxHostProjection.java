package com.example.demo.web.project.dashboard.projection.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AwxHostProjection {

    String id;
    Long awxId;

    @QueryProjection
    public AwxHostProjection(String id, Long awxId) {
        this.id = id;
        this.awxId = awxId;
    }
}
