package com.example.demo.web.dashboard.command.service;

import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeQuery;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeResponse;

public interface IDashboardCommandService {

    FetchGameIdByGameTypeResponse getGameId(FetchGameIdByGameTypeQuery query);
}
