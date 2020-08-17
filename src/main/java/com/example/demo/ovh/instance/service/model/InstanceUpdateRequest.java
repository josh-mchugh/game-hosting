package com.example.demo.ovh.instance.service.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class InstanceUpdateRequest {

    String id;
    String name;
    InstanceStatus status;
    String ip4Address;
    String ip6Address;
    LocalDateTime instanceCreatedDate;
}
