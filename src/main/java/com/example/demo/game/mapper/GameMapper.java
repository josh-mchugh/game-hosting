package com.example.demo.game.mapper;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.model.Game;

public class GameMapper {

    public static Game map(GameEntity entity) {

        if (entity == null) {

            return null;
        }

        return Game.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
    }
}
