package com.example.demo.user.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class FetchUserDashboardProjection {

    boolean emailValidated;
    boolean projects;

    @QueryProjection
    public FetchUserDashboardProjection(boolean emailValidated, boolean projects) {

        this.emailValidated = emailValidated;
        this.projects = projects;
    }
}
