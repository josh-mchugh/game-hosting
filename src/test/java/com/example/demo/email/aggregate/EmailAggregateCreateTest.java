package com.example.demo.email.aggregate;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.entity.EmailTemplate;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EmailAggregateCreateTest {

    private FixtureConfiguration<EmailAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(EmailAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectEvents() {

        UUID id = UUID.randomUUID();

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(id)
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .bodyContext("body", "context")
                .subjectContext("test")
                .build();

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(id)
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .bodyContext("body", "context")
                .subjectContext("test")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandIsMinValidThenExpectEvent() {

        UUID id = UUID.randomUUID();

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(id)
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(id)
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullTemplateThenExpectException() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .template(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void  whenCommandHasNullToAddressThenExpectException() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankToAddressThenExpectException() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
