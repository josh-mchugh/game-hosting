package com.example.demo.project.projection;

import com.example.demo.project.projection.model.FetchProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardQuery;

public interface IProjectProjector {

    FetchProjectDashboardProjection fetchProjectDashboard(FetchProjectDashboardQuery query);
}
