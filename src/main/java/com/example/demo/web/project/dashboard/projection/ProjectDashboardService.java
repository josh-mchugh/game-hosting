package com.example.demo.web.project.dashboard.projection;

import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsResponse;

public interface ProjectDashboardService {

    FetchProjectDetailsResponse getProjectDetails(FetchProjectDetailsQuery query);

    FetchAwxHostByInstanceOvhIdResponse fetchAwxHostByInstanceId(FetchAwxHostByInstanceOvhIdQuery query);
}
