package com.example.demo.awx.host.aggregate;

import com.example.demo.awx.host.aggregate.command.AwxHostCreateCommand;
import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostCreateCommandTest {

    private FixtureConfiguration<AwxHostAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxHostAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateCommandIsValidThenExecuteCreatedEvent() {

        UUID id = UUID.randomUUID();

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(id)
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(id)
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(null)
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullAwxInventoryIdThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(null)
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankAwxInventoryIdThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullInstanceIdThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId(null)
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankInstanceIdThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId("")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullHostIdThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(null)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullHostNameThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname(null)
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankHostNameThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("")
                .description("description")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullDescriptionThenExecuteSuccessfully() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description(null)
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCommandHasBlankDescriptionThenExecuteSuccessfully() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description("")
                .enabled(true)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCommandHasNullEnabledThenThrowException() {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
