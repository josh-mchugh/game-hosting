package com.example.demo.web.project.command.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectInstanceStartRequest {

    String projectId;
    String instanceId;
}