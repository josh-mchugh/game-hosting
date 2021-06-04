package com.example.demo.web.project.util.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectStateValidationRequest {

    UUID id;
    ProjectStatus currentStatus;
    ProjectState currentState;
    ProjectStatus expectedStatus;
    ProjectState expectedState;
}
