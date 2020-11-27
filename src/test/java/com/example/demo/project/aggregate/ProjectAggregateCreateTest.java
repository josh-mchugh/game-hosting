package com.example.demo.project.aggregate;

import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAggregateCreateTest {

    private FixtureConfiguration<ProjectAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(ProjectAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectSuccess() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .userId("userId")
                .gameId("gameId")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(null)
                .name("name")
                .userId("userId")
                .gameId("gameId")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullNameThenExpectException() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(null)
                .userId("userId")
                .gameId("gameId")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankNameThenExpectException() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("")
                .userId("userId")
                .gameId("gameId")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullUserIdThenExpectException() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .userId(null)
                .gameId("gameId")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankUserIdThenExpectException() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .userId("")
                .gameId("gameId")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullGameIdThenExpectException() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .userId("userId")
                .gameId(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankGameIdThenExpectException() {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .userId("userId")
                .gameId("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
