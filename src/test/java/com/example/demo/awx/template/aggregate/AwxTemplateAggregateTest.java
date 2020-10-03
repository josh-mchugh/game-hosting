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

public class AwxTemplateAggregateTest {

    private FixtureConfiguration<AwxTemplateAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxTemplateAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateCommandIsValidThenExecuteCreatedEvent() {

        UUID id = UUID.randomUUID();

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(id)
                .awxCredentialId("awx-credentials-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(id)
                .awxCredentialId("awx-credentials-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
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
                .awxCredentialId("awx-credentials-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
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
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);

    }

    @Test
    public void whenCreateCommandHasBlankAwxCredentialsIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
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
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId(null)
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankAwxInventoryIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
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
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId(null)
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankAwxPlaybookIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullTemplateIdThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(null)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
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
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name(null)
                .description("description")
                .jobType(TemplateJobType.RUN)
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
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("")
                .description("description")
                .jobType(TemplateJobType.RUN)
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
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description(null)
                .jobType(TemplateJobType.RUN)
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
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullJobTypeThenThrowException() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(null)
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
                .awxCredentialId("awx-credential-id")
                .awxPlaybookId("awx-playbook-id")
                .awxInventoryId("awx-inventory-id")
                .templateId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
                .verbosity(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
