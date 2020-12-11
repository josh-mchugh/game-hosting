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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(id)
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullAwxOrganizationIdThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankAwxOrganizationIdThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId("")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullAwxIdThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId("awxOrganizationId")
                .awxId(null)
                .name("name")
                .description("description")
                .type("type")
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name(null)
                .description("description")
                .type("type")
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("")
                .description("description")
                .type("type")
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description(null)
                .type("type")
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullTypeThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type(null)
                .webhookCallBackUrl("callback url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasEmptyTypeThenThrowException() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("")
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
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
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }
}
