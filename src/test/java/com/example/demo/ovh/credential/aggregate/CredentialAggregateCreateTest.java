package com.example.demo.ovh.credential.aggregate;

import com.example.demo.ovh.credential.aggregate.command.CredentialCreateCommand;
import com.example.demo.ovh.credential.entity.CredentialType;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

public class CredentialAggregateCreateTest {

    private FixtureConfiguration<CredentialAggregate> fixture;

    @BeforeEach
    public void setup() {

        StringEncryptor encryptor = Mockito.mock(StringEncryptor.class);
        Mockito.when(encryptor.encrypt(Mockito.anyString())).thenReturn("");

        fixture = new AggregateTestFixture<>(CredentialAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>())
                .registerInjectableResource(encryptor);
    }

    @Test
    public void whenAggregateIsValidThenExpectSuccessful() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId("ssh-key-id")
                .name("name")
                .publicKey("public-key")
                .type(CredentialType.ANSIBLE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenAggregateHasNullIdThenThrowException() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenAggregateHasNullSshKeyIdThenThrowException() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenAggregateHasBlankSshKeyIdThenThrowException() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenAggregateHasNullNameThenExpectSuccess() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId("ssh-key-id")
                .name(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenAggregateHasBlankNameThenExpectSuccess() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId("ssh-key-id")
                .name("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenAggregateHasNullPublicKeyThenExpectSuccess() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId("ssh-key-id")
                .name("name")
                .publicKey(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenAggregateHasBlankPublicKeyThenExpectSuccess() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId("ssh-key-id")
                .name("name")
                .publicKey("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenAggregateHasNullTypeThenExpectSuccess() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .sshKeyId("ssh-key-id")
                .name("name")
                .publicKey("")
                .type(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }
}
