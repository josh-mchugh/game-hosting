package com.example.demo.awx.project.aggregate;

import com.example.demo.awx.project.aggregate.command.AwxProjectCreateCommand;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

public class AwxProjectCreateCommandTest {

    private FixtureConfiguration<AwxProjectAggregate> fixture;

    @BeforeEach
    public void setup() {

        StringEncryptor encryptor = Mockito.mock(StringEncryptor.class);
        Mockito.when(encryptor.encrypt(Mockito.anyString())).thenReturn("");

        fixture = new AggregateTestFixture<>(AwxProjectAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>())
                .registerInjectableResource(encryptor);
    }

    @Test
    public void whenCreateIsValidThenExecuteSuccessfully() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullIdThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(null)
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullAwxOrganizationIdThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(null)
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullAwxCredentialIdThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(null)
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullAwxIdThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(null)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name(null)
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankNameThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullDescriptionThenExecuteSuccessfully() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description(null)
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankDescriptionThenExecuteSuccessfully() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullScmTypeThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType(null)
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankScmTypeThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("")
                .scmBranch("branch")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullScmBranchThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch(null)
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankScmBranchThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("")
                .scmUrl("url")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullScmUrlThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankScmUrlThenThrowException() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxCredentialId("awxCredentialId")
                .awxOrganizationId(UUID.randomUUID())
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmBranch("branch")
                .scmUrl("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
