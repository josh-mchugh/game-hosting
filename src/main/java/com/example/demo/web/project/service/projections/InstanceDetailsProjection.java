package com.example.demo.web.project.service.projections;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class InstanceDetailsProjection {

    String id;
    InstanceStatus instanceStatus;
    String ipAddress;

    @QueryProjection
    public InstanceDetailsProjection(String id, InstanceStatus instanceStatus, String ipAddress) {

        this.id = id;
        this.instanceStatus = instanceStatus;
        this.ipAddress = ipAddress;
    }
}
