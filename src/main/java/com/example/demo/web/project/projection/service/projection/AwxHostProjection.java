package com.example.demo.web.project.projection.service.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class AwxHostProjection {

    UUID id;
    Long awxId;

    @QueryProjection
    public AwxHostProjection(String id, Long awxId) {
        this.id = UUID.fromString(id);
        this.awxId = awxId;
    }
}
