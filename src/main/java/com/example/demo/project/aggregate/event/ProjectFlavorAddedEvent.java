package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectFlavorAddedEvent {

    UUID id;
    UUID ovhFlavorId;
    ProjectState state;
}
