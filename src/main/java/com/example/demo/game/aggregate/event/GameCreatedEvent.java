package com.example.demo.game.aggregate.event;

import com.example.demo.game.entity.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GameCreatedEvent {

    private final UUID id;
    private final GameType type;
}
