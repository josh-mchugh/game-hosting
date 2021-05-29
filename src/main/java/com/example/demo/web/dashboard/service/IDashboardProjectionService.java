package com.example.demo.web.dashboard.service;

import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsQuery;
import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsResponse;

public interface IDashboardProjectionService {

    FetchDashboardDetailsResponse fetchUserDashboard(FetchDashboardDetailsQuery query);
}
