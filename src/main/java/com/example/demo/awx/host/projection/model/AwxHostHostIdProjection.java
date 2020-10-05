package com.example.demo.awx.host.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AwxHostHostIdProjection {

    String awxHostId;
    Long hostId;

    @QueryProjection
    public AwxHostHostIdProjection(String awxHostId, Long hostId) {

        this.awxHostId = awxHostId;
        this.hostId = hostId;
    }
}
