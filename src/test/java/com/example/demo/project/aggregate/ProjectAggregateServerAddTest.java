package com.example.demo.project.aggregate;

import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.aggregate.command.ProjectServerAddCommand;
import com.example.demo.project.aggregate.event.ProjectServerAddedEvent;
import com.example.demo.project.entity.ProjectState;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAggregateServerAddTest {

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
        UUID imageId = UUID.randomUUID();

        ProjectServerAddCommand command = ProjectServerAddCommand.builder()
                .id(id)
                .ovhFlavorId(flavorId)
                .ovhImageId(imageId)
                .build();

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(id)
                .ovhFlavorId(flavorId)
                .ovhImageId(imageId)
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
        UUID imageId = UUID.randomUUID();

        ProjectServerAddCommand command = ProjectServerAddCommand.builder()
                .id(null)
                .ovhFlavorId(flavorId)
                .ovhImageId(imageId)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasHasNullFlavorIdThenExpectException() {

        UUID id = UUID.randomUUID();
        UUID imageId = UUID.randomUUID();

        ProjectServerAddCommand command = ProjectServerAddCommand.builder()
                .id(id)
                .ovhFlavorId(null)
                .ovhImageId(imageId)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasHasNullImageIdThenExpectException() {

        UUID id = UUID.randomUUID();
        UUID flavorId = UUID.randomUUID();

        ProjectServerAddCommand command = ProjectServerAddCommand.builder()
                .id(id)
                .ovhFlavorId(flavorId)
                .ovhImageId(null)
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
