package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectState;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectServerAddedEvent {

    UUID id;
    UUID ovhFlavorId;
    UUID ovhImageId;
    ProjectState state;
}
