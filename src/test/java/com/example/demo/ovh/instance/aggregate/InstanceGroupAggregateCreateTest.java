package com.example.demo.ovh.instance.aggregate;

import com.example.demo.ovh.instance.aggregate.command.InstanceGroupCreateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class InstanceGroupAggregateCreateTest {

    private FixtureConfiguration<InstanceGroupAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(InstanceGroupAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectEvents() {

        UUID id = UUID.randomUUID();

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(id)
                .projectId("projectId")
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(id)
                .projectId("projectId")
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(null)
                .projectId("projectId")
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullProjectIdThenExpectException() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(UUID.randomUUID())
                .projectId(null)
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankProjectIdThenExpectException() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(UUID.randomUUID())
                .projectId("")
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullGroupIdThenExpectException() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(UUID.randomUUID())
                .projectId("projectId")
                .groupId(null)
                .name("name")
                .type("type")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankGroupIdThenExpectException() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(UUID.randomUUID())
                .projectId("projectId")
                .groupId("")
                .name("name")
                .type("type")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}