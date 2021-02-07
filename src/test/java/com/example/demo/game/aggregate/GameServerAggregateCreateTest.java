package com.example.demo.game.aggregate;

import com.example.demo.game.aggregate.command.GameServerCreateCommand;
import com.example.demo.game.aggregate.event.GameServerCreatedEvent;
import com.example.demo.game.entity.GameServerStatus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameServerAggregateCreateTest {

    private FixtureConfiguration<GameServerAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(GameServerAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectEvents() {

        UUID id = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        UUID regionId = UUID.randomUUID();
        UUID flavorId = UUID.randomUUID();
        UUID imageId = UUID.randomUUID();

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(id)
                .gameId(gameId)
                .regionId(regionId)
                .flavorId(flavorId)
                .imageId(imageId)
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(id)
                .gameId(gameId)
                .regionId(regionId)
                .flavorId(flavorId)
                .imageId(imageId)
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdTheExpectException() {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(null)
                .gameId(UUID.randomUUID())
                .regionId(UUID.randomUUID())
                .flavorId(UUID.randomUUID())
                .imageId(UUID.randomUUID())
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullGameIdThenExpectException() {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .gameId(null)
                .regionId(UUID.randomUUID())
                .flavorId(UUID.randomUUID())
                .imageId(UUID.randomUUID())
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullRegionIdThenExpectException() {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .regionId(null)
                .flavorId(UUID.randomUUID())
                .imageId(UUID.randomUUID())
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullFlavorIdThenExpectException() {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .regionId(UUID.randomUUID())
                .flavorId(null)
                .imageId(UUID.randomUUID())
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullImageIdThenExpectException() {
        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .regionId(UUID.randomUUID())
                .flavorId(UUID.randomUUID())
                .imageId(null)
                .name("name")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);

    }

    @Test
    public void whenCommandHasNullNameThenExpectException() {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .regionId(UUID.randomUUID())
                .flavorId(UUID.randomUUID())
                .imageId(UUID.randomUUID())
                .name(null)
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankNameThenExpectException() {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .regionId(UUID.randomUUID())
                .flavorId(UUID.randomUUID())
                .imageId(UUID.randomUUID())
                .name("")
                .description("description")
                .status(GameServerStatus.ACTIVE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullStatusThenExpectException() {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .regionId(UUID.randomUUID())
                .flavorId(UUID.randomUUID())
                .imageId(UUID.randomUUID())
                .name("name")
                .description("description")
                .status(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
