package com.example.demo.project.aggregate;

import com.example.demo.project.aggregate.command.ProjectBillingAddCommand;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAggregateBillingAddTest {

    private FixtureConfiguration<ProjectAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(ProjectAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectSuccess() {

        UUID id = UUID.randomUUID();

        ProjectBillingAddCommand command = new ProjectBillingAddCommand(id);

        ProjectBillingAddedEvent event = ProjectBillingAddedEvent.builder()
                .id(id)
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UUID id = UUID.randomUUID();

        ProjectBillingAddCommand command = new ProjectBillingAddCommand(null);

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
