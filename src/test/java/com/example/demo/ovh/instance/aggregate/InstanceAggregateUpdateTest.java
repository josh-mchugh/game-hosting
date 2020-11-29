package com.example.demo.ovh.instance.aggregate;

import com.example.demo.ovh.instance.aggregate.command.InstanceCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceUpdateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceUpdatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceAggregateUpdateTest {

    private FixtureConfiguration<InstanceAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(InstanceAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectEvents() {

        UUID id = UUID.randomUUID();
        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .id(id)
                .name("new name")
                .status(InstanceStatus.DELETED)
                .instanceCreatedDate(instanceCreatedDate)
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .build();

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(id)
                .name("new name")
                .status(InstanceStatus.DELETED)
                .instanceCreatedDate(instanceCreatedDate)
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UUID id = UUID.randomUUID();

        InstanceUpdateCommand command = InstanceUpdateCommand.builder()
                .id(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    private InstanceCreateCommand createCommand(UUID id) {

        return InstanceCreateCommand.builder()
                .id(id)
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("isntanceId")
                .status(InstanceStatus.ACTIVE)
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 29, 13, 12))
                .build();
    }
}
