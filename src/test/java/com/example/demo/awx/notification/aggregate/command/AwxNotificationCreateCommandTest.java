package com.example.demo.awx.notification.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxNotificationCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasAwxOrganizationIdThenReturnAwxOrganizationId() {

        UUID awxOrganizationId = UUID.randomUUID();

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .awxOrganizationId(awxOrganizationId)
                .build();

        Assertions.assertEquals(awxOrganizationId, command.getAwxOrganizationId());
    }

    @Test
    public void whenCommandHasAwxIdThenReturnAwxId() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, command.getAwxId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasDescriptionThenReturnDescription() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", command.getDescription());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", command.getType());
    }

    @Test
    public void whenCommandHasWebCallBackUrlThenReturnWebCallBackUrl() {

        AwxNotificationCreateCommand command = AwxNotificationCreateCommand.builder()
                .webhookCallBackUrl("url")
                .build();

        Assertions.assertEquals("url", command.getWebhookCallBackUrl());
    }

    @Test
    public void whenCommandToString() {

        AwxNotificationCreateCommand command = command();

        String expected = "AwxNotificationCreateCommand(id=6c02ea5d-300c-4af2-adb9-906abaa4bbb8, awxOrganizationId=2bde1336-68b5-4121-a1b6-ca4f21a123a3, awxId=1, name=name, description=description, type=type, webhookCallBackUrl=url)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxNotificationCreateCommand command = command();

        Assertions.assertEquals(-835238384, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxNotificationCreateCommand command1 = command();
        AwxNotificationCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxNotificationCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxNotificationCreateCommand.builder().build());
    }

    private AwxNotificationCreateCommand command() {

        return AwxNotificationCreateCommand.builder()
                .id(UUID.fromString("6c02ea5d-300c-4af2-adb9-906abaa4bbb8"))
                .awxOrganizationId(UUID.fromString("2bde1336-68b5-4121-a1b6-ca4f21a123a3"))
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("url")
                .build();
    }
}
