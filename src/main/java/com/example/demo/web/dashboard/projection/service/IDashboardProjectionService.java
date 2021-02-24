package com.example.demo.web.dashboard.projection.service;

import com.example.demo.web.dashboard.projection.service.model.FetchDashboardDetailsQuery;
import com.example.demo.web.dashboard.projection.service.model.FetchDashboardDetailsResponse;

public interface IDashboardProjectionService {

    FetchDashboardDetailsResponse fetchUserDashboard(FetchDashboardDetailsQuery query);
}
