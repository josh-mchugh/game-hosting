package com.example.demo.awx.host.aggregate;

import com.example.demo.awx.host.aggregate.command.AwxHostDisableCommand;
import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostDisabledEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostDisableCommandTest {

    private FixtureConfiguration<AwxHostAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxHostAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExecuteEvent() {

        AwxHostCreatedEvent createdEvent = createdEvent();

        AwxHostDisableCommand disableCommand = new AwxHostDisableCommand(createdEvent.getId());
        AwxHostDisabledEvent disabledEvent = new AwxHostDisabledEvent(createdEvent.getId());

        fixture.given(createdEvent)
                .when(disableCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(disabledEvent);
    }

    @Test
    public void whenCommandHasNullIdThenThrowException() {

        AwxHostCreatedEvent createdEvent = createdEvent();

        fixture.given(createdEvent)
                .when(new AwxHostDisableCommand(null))
                .expectException(JSR303ViolationException.class);
    }

    private AwxHostCreatedEvent createdEvent() {

        UUID id =UUID.randomUUID();

        return AwxHostCreatedEvent.builder()
                .id(id)
                .awxInventoryId("awx-inventory-id")
                .instanceId("instance-id")
                .hostId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
    }
}
