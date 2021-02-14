package com.example.demo.ovh.instance.aggregate.command;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasFlavorIdThenReturnFlavorId() {

        UUID flavorId = UUID.randomUUID();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .flavorId(flavorId)
                .build();

        Assertions.assertEquals(flavorId, command.getFlavorId());
    }

    @Test
    public void whenCommandHasImageIdThenReturnImageId() {

        UUID imageId = UUID.randomUUID();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .imageId(imageId)
                .build();

        Assertions.assertEquals(imageId, command.getImageId());
    }

    @Test
    public void whenCommandHasCredentialIdThenReturnCredentialId() {

        UUID credentialId = UUID.randomUUID();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .credentialId(credentialId)
                .build();

        Assertions.assertEquals(credentialId, command.getCredentialId());
    }

    @Test
    public void whenCommandHasInstanceGroupIdThenReturnInstanceGroupId() {

        UUID instanceGroupId = UUID.randomUUID();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .instanceGroupId(instanceGroupId)
                .build();

        Assertions.assertEquals(instanceGroupId, command.getInstanceGroupId());
    }

    @Test
    public void whenCommandHasOvhIdThenReturnOvhId() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", command.getOvhId());
    }

    @Test
    public void whenCommandHasStatusThenReturnStatus() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .status(InstanceStatus.ACTIVE)
                .build();

        Assertions.assertEquals(InstanceStatus.ACTIVE, command.getStatus());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        Assertions.assertEquals(instanceCreatedDate, command.getInstanceCreatedDate());
    }

    @Test
    public void whenCommandToString() {

        InstanceCreateCommand command = command();

        String expected = "InstanceCreateCommand(id=1d156e1d-993c-438a-a51d-3160d99bb373, flavorId=223bbe35-60f2-49bb-9f1a-86a27ef7e6ff, imageId=7deda329-428a-4d43-a94f-8992fd7294b2, credentialId=97935b99-0e20-40f9-b336-b9294924f430, instanceGroupId=752fec29-b3b6-40cf-a6a2-6463f7520e79, ovhId=ovhId, status=ACTIVE, name=name, instanceCreatedDate=2020-11-28T21:57)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        InstanceCreateCommand command = InstanceCreateCommand.builder()
                .id(UUID.fromString("1d156e1d-993c-438a-a51d-3160d99bb373"))
                .flavorId(UUID.fromString("223bbe35-60f2-49bb-9f1a-86a27ef7e6ff"))
                .imageId(UUID.fromString("7deda329-428a-4d43-a94f-8992fd7294b2"))
                .credentialId(UUID.fromString("97935b99-0e20-40f9-b336-b9294924f430"))
                .instanceGroupId(UUID.fromString("752fec29-b3b6-40cf-a6a2-6463f7520e79"))
                .ovhId("ovhId")
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 21, 57))
                .build();

        Assertions.assertEquals(632838627, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        InstanceCreateCommand command1 = command();
        InstanceCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        InstanceCreateCommand command = command();

        Assertions.assertNotEquals(command, InstanceCreateCommand.builder().build());
    }

    private InstanceCreateCommand command() {

        return InstanceCreateCommand.builder()
                .id(UUID.fromString("1d156e1d-993c-438a-a51d-3160d99bb373"))
                .flavorId(UUID.fromString("223bbe35-60f2-49bb-9f1a-86a27ef7e6ff"))
                .imageId(UUID.fromString("7deda329-428a-4d43-a94f-8992fd7294b2"))
                .credentialId(UUID.fromString("97935b99-0e20-40f9-b336-b9294924f430"))
                .instanceGroupId(UUID.fromString("752fec29-b3b6-40cf-a6a2-6463f7520e79"))
                .ovhId("ovhId")
                .status(InstanceStatus.ACTIVE)
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 28, 21, 57))
                .build();
    }
}
