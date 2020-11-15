package com.example.demo.game.aggregate;

import com.example.demo.game.aggregate.command.GameCreateCommand;
import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.GameType;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameAggregateCreateTest {

    private FixtureConfiguration<GameAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(GameAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateHasValidThenExecuteEvents() {

        UUID id = UUID.randomUUID();

        GameCreateCommand command = GameCreateCommand.builder()
                .id(id)
                .type(GameType.MINECRAFT_JAVA)
                .build();

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(id)
                .type(GameType.MINECRAFT_JAVA)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateHasNullIdTheThrowException() {

        GameCreateCommand command = GameCreateCommand.builder()
                .id(null)
                .type(GameType.MINECRAFT_JAVA)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullTypeThenThrowException() {

        GameCreateCommand command = GameCreateCommand.builder()
                .id(UUID.randomUUID())
                .type(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
