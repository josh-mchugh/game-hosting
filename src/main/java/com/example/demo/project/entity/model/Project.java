package com.example.demo.project.entity.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class Project {

    String id;
    String name;
    ProjectStatus status;
    ProjectState state;
}
