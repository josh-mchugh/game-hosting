package com.example.demo.awx.credential.aggregate;

import com.example.demo.awx.credential.aggregate.command.AwxCredentialCreateCommand;
import com.example.demo.awx.credential.entity.AwxCredentialType;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class AwxCredentialCreateCommandTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    private FixtureConfiguration<AwxCredentialAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(AwxCredentialAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>())
                .registerInjectableResource(stringEncryptor);
    }

    @Test
    public void whenCreateCommandIsValidThenExecuteSuccessfully() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullIdThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(null)
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullOrganizationIdThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(null)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullCredentialIdThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(null)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullNameThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name(null)
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankNameThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullDescriptionThenExecuteSuccessfully() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description(null)
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankDescriptionThenExecuteSuccessfully() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullPrivateKeyThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey(null)
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankPrivateKeyThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullPassphraseThenExecuteSuccessfully() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase(null)
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankPassphraseThenExecuteSuccessfully() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("")
                .type(AwxCredentialType.MACHINE)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullTypeThenThrowException() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .credentialId(1L)
                .name("name")
                .description("description")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
