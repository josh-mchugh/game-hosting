package com.example.demo.web.dashboard.command.service;

import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateResponse;

public interface IDashboardCommandService {

    DashboardProjectCreateResponse handleProjectCreate(DashboardProjectCreateRequest request);
}
