package com.example.demo.web.project.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectInstanceStopRequest {

    String projectId;
    String instanceId;
}
