package com.example.demo.awx.template.aggregate;

import com.example.demo.awx.template.aggregate.command.AwxTemplateCreateCommand;
import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxTemplateCreateCommandTest {

    private FixtureConfiguration<AwxTemplateAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxTemplateAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateCommandIsValidThenExecuteCreatedEvent() {

        UUID id = UUID.randomUUID();
        UUID awxInventoryId = UUID.randomUUID();
        UUID awxPlaybookId = UUID.randomUUID();

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(id)
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(awxPlaybookId)
                .awxInventoryId(awxInventoryId)
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(id)
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(awxPlaybookId)
                .awxInventoryId(awxInventoryId)
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateCommandHasNullIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(null)
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullAwxCredentialsIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(null)
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);

    }

    @Test
    public void whenCreateCommandHasNullAwxInventoryIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullAwxPlaybookIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(null)
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullAwxIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(null)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullNameThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name(null)
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankNameThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullDescriptionThenExecuteSuccessfully() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description(null)
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankDescriptionThenExecuteSuccessfully() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullTypeThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .type(null)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullVerbosityThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxPlaybookId(UUID.randomUUID())
                .awxInventoryId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
