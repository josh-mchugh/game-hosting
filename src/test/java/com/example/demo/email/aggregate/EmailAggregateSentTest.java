package com.example.demo.email.aggregate;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.aggregate.command.EmailSentCommand;
import com.example.demo.email.aggregate.event.EmailSentEvent;
import com.example.demo.email.entity.EmailTemplateType;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmailAggregateSentTest {

    private FixtureConfiguration<EmailAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(EmailAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCommandIsValidThenExpectEvent() {

        UUID id = UUID.randomUUID();
        LocalDateTime sentDate = LocalDateTime.now();

        EmailCreateCommand createCommand = createCommand(id);

        EmailSentCommand command = EmailSentCommand.builder()
                .id(id)
                .sentDate(sentDate)
                .build();

        EmailSentEvent event = EmailSentEvent.builder()
                .id(id)
                .sentDate(sentDate)
                .build();

        fixture.givenCommands(createCommand)
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        EmailSentCommand command = EmailSentCommand.builder()
                .id(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullSentDateThenExpectException() {

        EmailSentCommand command = EmailSentCommand.builder()
                .id(UUID.randomUUID())
                .sentDate(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    private EmailCreateCommand createCommand(UUID id) {

        return EmailCreateCommand.builder()
                .id(id)
                .template(EmailTemplateType.WELCOME)
                .toAddress("toAddress")
                .build();
    }
}
