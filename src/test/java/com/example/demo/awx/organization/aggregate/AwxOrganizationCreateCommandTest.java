package com.example.demo.awx.organization.aggregate;

import com.example.demo.awx.organization.aggregate.command.AwxOrganizationCreateCommand;
import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxOrganizationCreateCommandTest {

    private FixtureConfiguration<AwxOrganizationAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxOrganizationAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateCommandIsValidThenExecuteEvents() {

        UUID id = UUID.randomUUID();

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(id)
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(id)
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateHasNullIdThenThrowException() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(null)
                .organizationId(1L)
                .name("name")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullOrganizationIdThenThrowException() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(null)
                .name("name")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name(null)
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankNameThenThrowException() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullDescriptionThenExecuteSuccessfully() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("name")
                .description(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankDescriptionThenExecuteSuccessfully() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("name")
                .description("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }
}
