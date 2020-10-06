package com.example.demo.awx.playbook.aggregate;

import com.example.demo.awx.playbook.aggregate.command.AwxPlaybookCreateCommand;
import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;
import com.example.demo.awx.playbook.entity.PlaybookType;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxPlaybookCreateCommandTest {

    private FixtureConfiguration<AwxPlaybookAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxPlaybookAggregate.class)
            .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreatedCommandIsValidThenExecuteCreatedEvent() {

        UUID id = UUID.randomUUID();

        AwxPlaybookCreatedEvent createdEvent = AwxPlaybookCreatedEvent.builder()
                .id(id)
                .awxProjectId("project-id")
                .name("cowsay-playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();

        AwxPlaybookCreateCommand createCommand = AwxPlaybookCreateCommand.builder()
                .id(id)
                .awxProjectId("project-id")
                .name("cowsay-playbook.yml")
                .build();

        fixture.givenNoPriorActivity()
                .when(createCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(createdEvent);
    }

    @Test
    public void whenCreatedCommandHasInvalidNameThenThrowException() {

        UUID id = UUID.randomUUID();

        AwxPlaybookCreateCommand createCommand = AwxPlaybookCreateCommand.builder()
                .id(id)
                .awxProjectId("project-id")
                .name("invalid-playbook.yml")
                .build();

        fixture.givenNoPriorActivity()
                .when(createCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void whenCreateCommandHasNullIdThenThrowException() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .awxProjectId("project-id")
                .name("invalid-playbook.yml")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullProjectIdThenThrowException() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("invalid-playbook.yml")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankProjectIdThenThrowException() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxProjectId("")
                .name("invalid-playbook.yml")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullNameThenThrowException() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxProjectId("project-id")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankNameIdThenThrowException() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxProjectId("project-id")
                .name("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
