package com.example.demo.email.aggregate;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.aggregate.command.EmailFailCommand;
import com.example.demo.email.aggregate.event.EmailFailedEvent;
import com.example.demo.email.entity.EmailTemplate;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EmailAggregateFailTest {

    private FixtureConfiguration<EmailAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(EmailAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectEvent() {

        UUID id = UUID.randomUUID();

        EmailCreateCommand createCommand = createCommand(id);

        EmailFailCommand command = new EmailFailCommand(id);

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(id)
                .build();

        fixture.givenCommands(createCommand)
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        EmailFailCommand command = new EmailFailCommand(null);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    private EmailCreateCommand createCommand(UUID id) {

        return EmailCreateCommand.builder()
                .id(id)
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();
    }
}
