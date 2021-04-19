package com.example.demo.awx.organization.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxOrganizationCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }
    
    @Test
    public void whenCommandHaAwxIdThenReturnAwxId() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, command.getAwxId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasDescriptionThenReturnDescription() {

        AwxOrganizationCreateCommand command = AwxOrganizationCreateCommand.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", command.getDescription());
    }

    @Test
    public void whenCommandToString() {

        AwxOrganizationCreateCommand command = command();

        String expected = "AwxOrganizationCreateCommand(id=2f52c7a8-dc99-4371-9687-9f50f650583f, awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxOrganizationCreateCommand command = command();

        Assertions.assertEquals(19103615, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxOrganizationCreateCommand command1 = command();
        AwxOrganizationCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxOrganizationCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxOrganizationCreateCommand.builder().build());
    }

    private AwxOrganizationCreateCommand command() {

        return AwxOrganizationCreateCommand.builder()
                .id(UUID.fromString("2f52c7a8-dc99-4371-9687-9f50f650583f"))
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
