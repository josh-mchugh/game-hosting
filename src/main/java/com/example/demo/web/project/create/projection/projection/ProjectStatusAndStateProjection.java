package com.example.demo.web.project.create.projection.projection;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class ProjectStatusAndStateProjection {

    ProjectStatus status;
    ProjectState state;

    @QueryProjection
    public ProjectStatusAndStateProjection(ProjectStatus status, ProjectState state) {
        this.status = status;
        this.state = state;
    }
}
