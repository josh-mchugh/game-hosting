package com.example.demo.ovh.instance.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class Instance {

    String id;
    String instanceId;
    String name;
    InstanceStatus status;
    LocalDateTime instanceCreatedDate;
    String ip4Address;
    String ip6Address;
}
