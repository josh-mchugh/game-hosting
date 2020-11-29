package com.example.demo.ovh.instance.aggregate;

import com.example.demo.ovh.instance.aggregate.command.InstanceCreateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceAggregateCreateTest {

    private FixtureConfiguration<InstanceAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(InstanceAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectEvent() {

        UUID id = UUID.randomUUID();
        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(id)
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(id)
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(null)
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullFlavorIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId(null)
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankFlavorIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullImageIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId(null)
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankImageIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullCredentialIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId(null)
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankCredentialIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullInstanceGroupIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId(null)
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankInstanceGroupIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("")
                .instanceId("instanceId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullInstanceIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId(null)
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankInstanceIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullStatusThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId("credentialId")
                .instanceGroupId("instanceGroupId")
                .instanceId("instanceId")
                .name("name")
                .status(null)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
