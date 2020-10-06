package com.example.demo.awx.notification.aggregate;

import com.example.demo.awx.notification.aggregate.command.AwxNotificationCreateCommand;
import com.example.demo.awx.notification.aggregate.event.AwxNotificationCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxNotificationCreateCommandTest {

    private FixtureConfiguration<AwxNotificationAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxNotificationAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateCommandIsValidThenExecuteCreatedEvent() {

        UUID id = UUID.randomUUID();

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(id)
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(id)
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateCommandHasNullIdThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(null)
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullOrganizationIdThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(null)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullNotificationIdThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(null)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullNameThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name(null)
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankNameThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name("")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullDescriptionThenExecuteSuccessfully() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description(null)
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
            .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankDescriptionThenExecuteSuccessfully() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("")
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullNotificationTypeThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType(null)
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasEmptyNotificationTypeThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullWebhookCallBackThenExecuteSuccessfully() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankWebhookCallBackThenExecuteSuccessfully() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .notificationId(1L)
                .name("name")
                .description("description")
                .notificationType("notification type")
                .webhookCallBackUrl("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }
}
