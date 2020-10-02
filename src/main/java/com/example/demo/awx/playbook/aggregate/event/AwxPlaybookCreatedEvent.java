package com.example.demo.awx.playbook.aggregate.event;

import com.example.demo.awx.playbook.entity.PlaybookType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxPlaybookCreatedEvent {

    UUID id;
    String awxProjectId;
    String name;
    PlaybookType type;
}
