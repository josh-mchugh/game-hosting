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
        UUID awxProjectId = UUID.randomUUID();

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(id)
                .awxProjectId(awxProjectId)
                .name("cowsay-playbook.yml")
                .build();

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(id)
                .awxProjectId(awxProjectId)
                .name("cowsay-playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreatedCommandHasInvalidNameThenThrowException() {

        UUID id = UUID.randomUUID();

        AwxPlaybookCreateCommand createCommand = AwxPlaybookCreateCommand.builder()
                .id(id)
                .awxProjectId(UUID.randomUUID())
                .name("invalid-playbook.yml")
                .build();

        fixture.givenNoPriorActivity()
                .when(createCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void whenCreateCommandHasNullIdThenThrowException() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(null)
                .awxProjectId(UUID.randomUUID())
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
                .awxProjectId(null)
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
                .awxProjectId(UUID.randomUUID())
                .name(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankNameIdThenThrowException() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxProjectId(UUID.randomUUID())
                .name("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasInvalidNameThenThrowException() {

        UUID id = UUID.randomUUID();

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(id)
                .awxProjectId(UUID.randomUUID())
                .name("invalid")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(IllegalArgumentException.class);
    }
}
