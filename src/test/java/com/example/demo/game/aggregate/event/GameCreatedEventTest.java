package com.example.demo.game.aggregate.event;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .type(GameType.MINECRAFT_JAVA)
                .build();

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, event.getType());
    }

    @Test
    public void whenEventToString() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(UUID.fromString("d49064e6-8ea5-4b24-a4d5-2904a2fa0edb"))
                .type(GameType.MINECRAFT_JAVA)
                .build();

        String toString = "GameCreatedEvent(id=d49064e6-8ea5-4b24-a4d5-2904a2fa0edb, type=MINECRAFT_JAVA)";

        Assertions.assertEquals(toString, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        GameCreatedEvent event = event();

        Assertions.assertEquals(973073523, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        GameCreatedEvent event1 = event();
        GameCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        GameCreatedEvent event = event();

        Assertions.assertNotEquals(event, GameCreatedEvent.builder().build());
    }

    private GameCreatedEvent event() {

        return GameCreatedEvent.builder()
                .id(UUID.fromString("d49064e6-8ea5-4b24-a4d5-2904a2fa0edb"))
                .build();
    }
}
