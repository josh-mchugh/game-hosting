package com.example.demo.awx.credential.aggregate.command;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxCredentialCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasOrganizationIdThenReturnOrganizationId() {

        UUID awxOrganizationId = UUID.randomUUID();

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .awxOrganizationId(awxOrganizationId)
                .build();

        Assertions.assertEquals(awxOrganizationId, command.getAwxOrganizationId());
    }

    @Test
    public void whenCommandHasCredentialIdThenReturnCredentialId() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, command.getAwxId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasDescriptionThenReturnDescription() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", command.getDescription());
    }

    @Test
    public void whenCommandHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .privateKey("privateKey")
                .build();

        Assertions.assertEquals("privateKey", command.getPrivateKey());
    }

    @Test
    public void whenCommandHasPassPhraseThenReturnPassPhrase() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals("passPhrase", command.getPassphrase());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertEquals(AwxCredentialType.MACHINE, command.getType());
    }

    @Test
    public void whenCommandToString() {

        AwxCredentialCreateCommand command = command();

        String expected = "AwxCredentialCreateCommand(id=614ac740-10e9-4c0a-b8d3-2c35ba46bd1b, awxOrganizationId=e25619be-7b4d-41ea-af35-d0058ab1bb37, awxId=1, name=name, description=description, privateKey=privateKey, passphrase=passPhrase, type=MACHINE)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.fromString("614ac740-10e9-4c0a-b8d3-2c35ba46bd1b"))
                .awxOrganizationId(UUID.fromString("e25619be-7b4d-41ea-af35-d0058ab1bb37"))
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals(1391014654, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxCredentialCreateCommand command1 = command();
        AwxCredentialCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxCredentialCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxCredentialCreateCommand.builder().build());
    }

    private AwxCredentialCreateCommand command() {

        return AwxCredentialCreateCommand.builder()
                .id(UUID.fromString("614ac740-10e9-4c0a-b8d3-2c35ba46bd1b"))
                .awxOrganizationId(UUID.fromString("e25619be-7b4d-41ea-af35-d0058ab1bb37"))
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .type(AwxCredentialType.MACHINE)
                .build();
    }

}
