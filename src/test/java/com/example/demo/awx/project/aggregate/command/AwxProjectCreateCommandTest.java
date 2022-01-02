package com.example.demo.awx.project.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxProjectCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasAwxOrganizationIdThenReturnAwxOrganizationId(){

        UUID awxOrganizationId = UUID.randomUUID();

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .awxOrganizationId(awxOrganizationId)
                .build();

        Assertions.assertEquals(awxOrganizationId, command.getAwxOrganizationId());
    }

    @Test
    public void whenCommandHasAwxCredentialIdThenReturnAwxCredentialId() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .awxCredentialId("awxCredentialId")
                .build();

        Assertions.assertEquals("awxCredentialId", command.getAwxCredentialId());
    }

    @Test
    public void whenCommandHasAwxIdThenReturnAwxId() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, command.getAwxId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasDescriptionThenReturnDescription() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", command.getDescription());
    }

    @Test
    public void whenCommandHasScmTypeThenReturnScmType() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .scmType("type")
                .build();

        Assertions.assertEquals("type", command.getScmType());
    }

    @Test
    public void whenCommandHasScmUrlThenReturnScmUrl() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .scmUrl("url")
                .build();

        Assertions.assertEquals("url", command.getScmUrl());
    }

    @Test
    public void whenCommandHasScmBranchThenReturnScmBranch() {

        AwxProjectCreateCommand command = AwxProjectCreateCommand.builder()
                .scmBranch("scmBranch")
                .build();

        Assertions.assertEquals("scmBranch", command.getScmBranch());
    }

    @Test
    public void whenCommandToString() {

        AwxProjectCreateCommand command = command();

        String expected = "AwxProjectCreateCommand(id=e55f95e5-a060-4a9e-9026-f95eb7fa5305, awxOrganizationId=b0697b6f-68c0-40f6-954d-fcb993096611, awxCredentialId=7b445f93-37ef-4ea8-97dd-12cb882e44d7, awxId=1, name=name, description=description, scmType=type, scmUrl=url, scmBranch=scmBranch)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxProjectCreateCommand command = command();

        Assertions.assertEquals(-1251962899, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxProjectCreateCommand command1 = command();
        AwxProjectCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxProjectCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxProjectCreateCommand.builder().build());
    }

    private AwxProjectCreateCommand command() {

        return AwxProjectCreateCommand.builder()
                .id(UUID.fromString("e55f95e5-a060-4a9e-9026-f95eb7fa5305"))
                .awxOrganizationId(UUID.fromString("b0697b6f-68c0-40f6-954d-fcb993096611"))
                .awxCredentialId("7b445f93-37ef-4ea8-97dd-12cb882e44d7")
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("type")
                .scmUrl("url")
                .scmBranch("scmBranch")
                .build();
    }
}
