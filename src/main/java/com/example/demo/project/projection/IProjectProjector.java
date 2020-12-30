package com.example.demo.project.projection;

import com.example.demo.project.projection.model.FetchProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardQuery;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdProjection;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdQuery;

public interface IProjectProjector {

    FetchProjectDashboardProjection fetchProjectDashboard(FetchProjectDashboardQuery query);

    FetchProjectDetailsByIdProjection fetchProjectDetails(FetchProjectDetailsByIdQuery query);
}
