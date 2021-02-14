package com.example.demo.game.entity.mapper;

import com.example.demo.game.entity.GameServerEntity;
import com.example.demo.game.entity.model.GameServer;

public class GameServerMapper {

    public static GameServer map(GameServerEntity entity) {

        if (entity == null) {

            return null;
        }

        return GameServer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }
}
