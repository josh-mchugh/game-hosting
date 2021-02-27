package com.example.demo.web.awx.service.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class ProjectProjection {

    UUID id;
    Long awxId;

    @QueryProjection
    public ProjectProjection(String id, Long awxId) {

        this.id = UUID.fromString(id);
        this.awxId = awxId;
    }
}
