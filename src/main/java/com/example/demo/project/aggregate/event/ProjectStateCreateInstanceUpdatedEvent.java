package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectStateCreateInstanceUpdatedEvent {

    UUID id;
    ProjectStatus status;
    ProjectState state;
}
