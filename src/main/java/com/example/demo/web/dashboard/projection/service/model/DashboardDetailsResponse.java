package com.example.demo.web.dashboard.projection.service.model;

import com.example.demo.project.projection.model.ProjectDashboardProjection;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class DashboardDetailsResponse {

    boolean emailVerified;
    boolean hasProjects;
    ImmutableList<ProjectDashboardProjection> projects;

    public boolean displayEmailVerification() {

        return !emailVerified;
    }

    public boolean displayCreateServer() {

        return emailVerified && !hasProjects;
    }

    public boolean displayProjectList() {

        return emailVerified && hasProjects;
    }
}