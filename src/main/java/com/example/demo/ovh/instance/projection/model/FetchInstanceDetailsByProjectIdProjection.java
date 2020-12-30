package com.example.demo.ovh.instance.projection.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchInstanceDetailsByProjectIdProjection {

    String ovhId;
    InstanceStatus instanceStatus;
    String ipAddress;

    @QueryProjection
    public FetchInstanceDetailsByProjectIdProjection(String ovhId, InstanceStatus instanceStatus, String ipAddress) {

        this.ovhId = ovhId;
        this.instanceStatus = instanceStatus;
        this.ipAddress = ipAddress;
    }
}
