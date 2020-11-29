package com.example.demo.ovh.instance.aggregate.event;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class InstanceUpdatedEvent {

    UUID id;
    String name;
    InstanceStatus status;
    String ip4Address;
    String ip6Address;
    LocalDateTime instanceCreatedDate;
}
