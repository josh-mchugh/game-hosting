package com.example.demo.web.dashboard.service.projections;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class DashboardDetailsProjection {

    boolean emailValidated;
    boolean projects;

    @QueryProjection
    public DashboardDetailsProjection(boolean emailValidated, boolean projects) {

        this.emailValidated = emailValidated;
        this.projects = projects;
    }
}
