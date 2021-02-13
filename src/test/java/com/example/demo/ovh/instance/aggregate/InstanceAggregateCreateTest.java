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
        UUID credentialId = UUID.randomUUID();
        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(id)
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId(credentialId)
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(id)
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId(credentialId)
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
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
                .ovhId("ovhId")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId(null)
                .ovhId("ovhId")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId("")
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullOvhIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId(null)
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankOvhIdThenExpectException() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.randomUUID())
                .flavorId("flavorId")
                .imageId("imageId")
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId("")
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
                .credentialId(UUID.randomUUID())
                .instanceGroupId("instanceGroupId")
                .ovhId("ovhId")
                .name("name")
                .status(null)
                .instanceCreatedDate(LocalDateTime.now())
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
