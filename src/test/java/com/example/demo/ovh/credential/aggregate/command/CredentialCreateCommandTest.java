package com.example.demo.ovh.credential.aggregate.command;

import com.example.demo.ovh.credential.entity.CredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CredentialCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasOvhIdThenReturnOvhId() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", command.getOvhId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasPublicKeyThenReturnPublicKey() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .publicKey("public-key")
                .build();

        Assertions.assertEquals("public-key", command.getPublicKey());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .type(CredentialType.ANSIBLE)
                .build();

        Assertions.assertEquals(CredentialType.ANSIBLE, command.getType());
    }

    @Test
    public void whenCommandToString() {

        CredentialCreateCommand command = credentialCreateCommand();

        String toString = "CredentialCreateCommand(id=815cd619-6369-4c8f-9c83-30710e379fae, ovhId=ovhId, name=name, publicKey=public-key, type=ANSIBLE)";

        Assertions.assertEquals(toString, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.fromString("815cd619-6369-4c8f-9c83-30710e379fae"))
                .ovhId("ovhId")
                .name("name")
                .publicKey("public-key")
                .build();

        Assertions.assertEquals(2120133999, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        CredentialCreateCommand command1 = credentialCreateCommand();
        CredentialCreateCommand command2 = credentialCreateCommand();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        CredentialCreateCommand command = credentialCreateCommand();

        Assertions.assertNotEquals(command, CredentialCreateCommand.builder().build());
    }

    private CredentialCreateCommand credentialCreateCommand() {

        return CredentialCreateCommand.builder()
                .id(UUID.fromString("815cd619-6369-4c8f-9c83-30710e379fae"))
                .ovhId("ovhId")
                .name("name")
                .publicKey("public-key")
                .type(CredentialType.ANSIBLE)
                .build();
    }
}
