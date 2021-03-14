package com.example.demo.ovh.instance.scheduler.projection.projection;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class InstanceProjection {

    UUID id;
    String ovhId;
    String name;
    LocalDateTime instanceCreatedDate;
    InstanceStatus status;
    String ip4Address;
    String ip6Address;

    @QueryProjection
    public InstanceProjection(String id, String ovhId, String name, LocalDateTime instanceCreatedDate, InstanceStatus status, String ip4Address, String ip6Address) {
        this.id = UUID.fromString(id);
        this.ovhId = ovhId;
        this.name = name;
        this.instanceCreatedDate = instanceCreatedDate;
        this.status = status;
        this.ip4Address = ip4Address;
        this.ip6Address = ip6Address;
    }
}
