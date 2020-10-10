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

public class GameCreateCommandTest {

    private FixtureConfiguration<GameAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(GameAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateHasValidThenExecuteEvents() {

        UUID id = UUID.randomUUID();

        GameCreateCommand command = new GameCreateCommand(id, GameType.MINECRAFT_JAVA);
        GameCreatedEvent event = new GameCreatedEvent(id, GameType.MINECRAFT_JAVA);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateHasNullIdTheThrowException() {

        GameCreateCommand command = new GameCreateCommand(null, GameType.MINECRAFT_JAVA);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullTypeThenThrowException() {

        GameCreateCommand command = new GameCreateCommand(UUID.randomUUID(), null);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
