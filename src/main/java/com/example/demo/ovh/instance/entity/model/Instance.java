package com.example.demo.ovh.instance.entity.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Instance {

    UUID id;
    String ovhId;
    String name;
    InstanceStatus status;
    LocalDateTime instanceCreatedDate;
    String ip4Address;
    String ip6Address;
}
