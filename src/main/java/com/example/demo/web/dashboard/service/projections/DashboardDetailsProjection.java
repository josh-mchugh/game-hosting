package com.example.demo.web.dashboard.service.projections;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DashboardDetailsProjection {

    private final boolean emailValidated;
    private final boolean projects;

    @QueryProjection
    public DashboardDetailsProjection(boolean emailValidated, boolean projects) {

        this.emailValidated = emailValidated;
        this.projects = projects;
    }
}
