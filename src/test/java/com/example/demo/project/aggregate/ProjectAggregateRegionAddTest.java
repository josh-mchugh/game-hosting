package com.example.demo.project.aggregate;

import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.aggregate.command.ProjectRegionAddCommand;
import com.example.demo.project.aggregate.event.ProjectRegionAddedEvent;
import com.example.demo.project.entity.ProjectState;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectAggregateRegionAddTest {

    private FixtureConfiguration<ProjectAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(ProjectAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectSuccess() {

        UUID id = UUID.randomUUID();
        UUID regionId = UUID.randomUUID();

        ProjectRegionAddCommand command = ProjectRegionAddCommand.builder()
                .id(id)
                .ovhRegionId(regionId)
                .build();

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(id)
                .ovhRegionId(regionId)
                .state(ProjectState.CONFIG_SERVER)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UUID id = UUID.randomUUID();
        UUID regionId = UUID.randomUUID();

        ProjectRegionAddCommand command = ProjectRegionAddCommand.builder()
                .id(null)
                .ovhRegionId(regionId)
                .build();

        fixture.givenCommands(projectCreateCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullRegionIdThenExpectException() {

        UUID id = UUID.randomUUID();

        ProjectRegionAddCommand command = ProjectRegionAddCommand.builder()
                .id(id)
                .ovhRegionId(null)
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
