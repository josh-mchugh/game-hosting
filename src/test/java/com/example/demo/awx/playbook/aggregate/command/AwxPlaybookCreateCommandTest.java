package com.example.demo.awx.playbook.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxPlaybookCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasAwxProjectIdThenReturnAwxProjectId() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .awxProjectId("awxProjectId")
                .build();

        Assertions.assertEquals("awxProjectId", command.getAwxProjectId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        AwxPlaybookCreateCommand command = AwxPlaybookCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandToString() {

        AwxPlaybookCreateCommand command = command();

        String expected = "AwxPlaybookCreateCommand(id=06540b93-9034-4ffe-96b4-ccd0272d06f1, awxProjectId=awxProjectId, name=name)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxPlaybookCreateCommand command = command();

        Assertions.assertEquals(1623560896, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxPlaybookCreateCommand command1 = command();
        AwxPlaybookCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxPlaybookCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxPlaybookCreateCommand.builder().build());
    }

    private AwxPlaybookCreateCommand command() {

        return AwxPlaybookCreateCommand.builder()
                .id(UUID.fromString("06540b93-9034-4ffe-96b4-ccd0272d06f1"))
                .awxProjectId("awxProjectId")
                .name("name")
                .build();
    }
}
