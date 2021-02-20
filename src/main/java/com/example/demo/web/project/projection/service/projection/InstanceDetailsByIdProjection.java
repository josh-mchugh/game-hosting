package com.example.demo.web.project.projection.service.projection;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class InstanceDetailsByIdProjection {

    String ovhId;
    InstanceStatus instanceStatus;
    String ipAddress;

    @QueryProjection
    public InstanceDetailsByIdProjection(String ovhId, InstanceStatus instanceStatus, String ipAddress) {

        this.ovhId = ovhId;
        this.instanceStatus = instanceStatus;
        this.ipAddress = ipAddress;
    }
}
