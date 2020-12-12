package com.example.demo.awx.inventory.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxInventoryCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasOrganizationIdThenReturnOrganizationId() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .awxOrganizationId("awxOrganizationId")
                .build();

        Assertions.assertEquals("awxOrganizationId", command.getAwxOrganizationId());
    }

    @Test
    public void whenCommandHasAwxIdThenReturnAwxId() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, command.getAwxId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasDescriptionThenReturnDescription() {

        AwxInventoryCreateCommand command = AwxInventoryCreateCommand.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", command.getDescription());
    }

    @Test
    public void whenCommandToString() {

        AwxInventoryCreateCommand command = command();

        String expected = "AwxInventoryCreateCommand(id=7fc48e20-1917-45ec-8817-da04cc54177f, awxOrganizationId=awxOrganizationId, awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxInventoryCreateCommand command = command();

        Assertions.assertEquals(356270688, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxInventoryCreateCommand command1 = command();
        AwxInventoryCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxInventoryCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxInventoryCreateCommand.builder().build());
    }

    private AwxInventoryCreateCommand command() {

        return AwxInventoryCreateCommand.builder()
                .id(UUID.fromString("7fc48e20-1917-45ec-8817-da04cc54177f"))
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
