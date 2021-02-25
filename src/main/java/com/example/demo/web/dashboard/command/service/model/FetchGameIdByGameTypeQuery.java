package com.example.demo.web.dashboard.command.service.model;

import com.example.demo.game.entity.GameType;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchGameIdByGameTypeQuery {

    GameType type;
}
