package com.example.demo.game.entity.mapper;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.model.Game;

public class GameMapper {

    public static Game map(GameEntity entity) {

        if (entity == null) {

            return null;
        }

        return Game.builder()
                .id(entity.getUUID())
                .type(entity.getType())
                .build();
    }
}
