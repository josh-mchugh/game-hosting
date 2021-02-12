package com.example.demo.awx.inventory.aggregate;

import com.example.demo.awx.inventory.aggregate.command.AwxInventoryCreateCommand;
import com.example.demo.awx.inventory.aggregate.event.AwxInventoryCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxInventoryCreateCommandTest {

    private FixtureConfiguration<AwxInventoryAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxInventoryAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateIsValidThenExecuteCreatedEvent() {

        UUID id = UUID.randomUUID();
        UUID awxOrganizationId = UUID.randomUUID();

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(id)
                .awxOrganizationId(awxOrganizationId)
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(id)
                .awxOrganizationId(awxOrganizationId)
                .awxId(1L)
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

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(null)
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullAwxOrganizationIdThenThrowException() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullInventoryIdThenThrowException() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(UUID.randomUUID())
                .awxId(null)
                .name("name")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name(null)
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankNameThenThrowException() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullDescriptionThenExecuteSuccessfully() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankDescriptionThenExecuteSuccessfully() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }
}
