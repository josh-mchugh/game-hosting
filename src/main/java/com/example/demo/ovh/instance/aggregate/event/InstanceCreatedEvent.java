package com.example.demo.ovh.instance.aggregate.event;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class InstanceCreatedEvent {

    UUID id;
    UUID flavorId;
    UUID imageId;
    UUID credentialId;
    UUID instanceGroupId;
    String ovhId;
    InstanceStatus status;
    String name;
    LocalDateTime instanceCreatedDate;
}
