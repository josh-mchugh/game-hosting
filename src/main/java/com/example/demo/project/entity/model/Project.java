package com.example.demo.project.entity.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Project {

    UUID id;
    String name;
    ProjectStatus status;
    ProjectState state;
}
