package com.example.demo.web.dashboard.service.model;

import com.example.demo.game.entity.GameType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class DashboardProjectCreateRequest {

    String name;
    GameType gameType;
    String region;
    String flavor;
}
