package com.example.demo.web.project.create.command.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectCreateRequest {

    String projectName;
    String gameId;
}
