package com.example.demo.web.project.service.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder")
public class ProjectInstanceStartRequest {

    String projectId;
    String instanceId;
}
