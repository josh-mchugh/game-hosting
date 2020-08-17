package com.example.demo.web.dashboard.service;

import com.example.demo.web.dashboard.service.model.DashboardDetailsResponse;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateResponse;

public interface IDashboardService {

    DashboardDetailsResponse getDashboardDetails();

    DashboardProjectCreateResponse handleDashboardProjectCreate(DashboardProjectCreateRequest request);
}
