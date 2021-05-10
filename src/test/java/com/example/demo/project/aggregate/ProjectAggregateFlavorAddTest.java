package com.example.demo.project.aggregate;

import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.aggregate.command.ProjectFlavorAddCommand;
import com.example.demo.project.aggregate.event.ProjectFlavorAddedEvent;
import com.example.demo.project.entity.ProjectState;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAggregateFlavorAddTest {

    private FixtureConfiguration<ProjectAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(ProjectAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectSuccess() {

        UUID id = UUID.randomUUID();
        UUID flavorId = UUID.randomUUID();

        ProjectFlavorAddCommand command = ProjectFlavorAddCommand.builder()
                .id(id)
                .ovhFlavorId(flavorId)
                .build();

        ProjectFlavorAddedEvent event = ProjectFlavorAddedEvent.builder()
                .id(id)
                .ovhFlavorId(flavorId)
                .state(ProjectState.CONFIG_BILLING)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UUID id = UUID.randomUUID();
        UUID flavorId = UUID.randomUUID();

        ProjectFlavorAddCommand command = ProjectFlavorAddCommand.builder()
                .id(null)
                .ovhFlavorId(flavorId)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasHasNullFlavorIdThenExpectException() {

        UUID id = UUID.randomUUID();

        ProjectFlavorAddCommand command = ProjectFlavorAddCommand.builder()
                .id(null)
                .ovhFlavorId(null)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    private ProjectCreateCommand projectCreateCommand(UUID id) {

        return ProjectCreateCommand.builder()
                .id(id)
                .name("name")
                .userId(UUID.randomUUID())
                .gameId(UUID.randomUUID())
                .build();
    }
}
