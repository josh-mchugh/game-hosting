package com.example.demo.awx.playbook.aggregate.event;

import com.example.demo.awx.playbook.entity.PlaybookType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxPlaybookCreatedEvent {

    private final UUID id;
    private final String awxProjectId;
    private final String name;
    private final PlaybookType type;
}
