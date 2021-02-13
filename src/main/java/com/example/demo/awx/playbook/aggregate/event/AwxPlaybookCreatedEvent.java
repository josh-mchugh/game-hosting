package com.example.demo.awx.playbook.aggregate.event;

import com.example.demo.awx.playbook.entity.PlaybookType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxPlaybookCreatedEvent {

    UUID id;
    UUID awxProjectId;
    String name;
    PlaybookType type;
}
