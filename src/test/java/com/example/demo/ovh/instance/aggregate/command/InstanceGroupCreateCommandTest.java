package com.example.demo.ovh.instance.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class InstanceGroupCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasProjectIdThenReturnProjectId() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .projectId("projectId")
                .build();

        Assertions.assertEquals("projectId", command.getProjectId());
    }

    @Test
    public void whenCommandHasGroupIdThenReturnGroupId() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .groupId("groupId")
                .build();

        Assertions.assertEquals("groupId", command.getGroupId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        InstanceGroupCreateCommand command = InstanceGroupCreateCommand.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", command.getType());
    }

    @Test
    public void whenCommandToString() {

        InstanceGroupCreateCommand command = command();

        String expected = "InstanceGroupCreateCommand(id=de3ffe16-c009-4502-9b38-b60281ff19df, projectId=projectId, groupId=groupId, name=name, type=type)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        InstanceGroupCreateCommand command = command();

        Assertions.assertEquals(1100961197, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        InstanceGroupCreateCommand command1 = command();
        InstanceGroupCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        InstanceGroupCreateCommand command = command();

        Assertions.assertNotEquals(command, InstanceGroupCreateCommand.builder().build());
    }

    private InstanceGroupCreateCommand command() {

        return InstanceGroupCreateCommand.builder()
                .id(UUID.fromString("de3ffe16-c009-4502-9b38-b60281ff19df"))
                .projectId("projectId")
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();
    }
}
