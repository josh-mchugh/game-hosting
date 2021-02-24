package com.example.demo.web.dashboard.projection.service.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class UserDashboardProjection {

    boolean emailValidated;
    boolean projects;

    @QueryProjection
    public UserDashboardProjection(boolean emailValidated, boolean projects) {

        this.emailValidated = emailValidated;
        this.projects = projects;
    }
}
