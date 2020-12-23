package com.example.demo.ovh.image.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageUpdateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasOvhIdThenReturnOvhId() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", command.getOvhId());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", command.getType());
    }

    @Test
    public void whenCommandHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageUpdateCommand  command = ImageUpdateCommand.builder()
                .imageCreatedDate(createdDate)
                .build();

        Assertions.assertEquals(createdDate, command.getImageCreatedDate());
    }

    @Test
    public void whenCommandHasFlavorTypeThenReturnFlavorType() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .flavorType("flavor-type")
                .build();

        Assertions.assertEquals("flavor-type", command.getFlavorType());
    }

    @Test
    public void whenCommandHasHourlyThenReturnHourly() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", command.getHourly());
    }

    @Test
    public void whenCommandHasMonthlyThenReturnMonthly() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", command.getMonthly());
    }

    @Test
    public void whenCommandHasSizeThenReturnSize() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .size(1.0D)
                .build();

        Assertions.assertEquals(1.0D, command.getSize());
    }

    @Test
    public void whenCommandHasMinRamThenReturnMinRam() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .minRam(1)
                .build();

        Assertions.assertEquals(1, command.getMinRam());
    }

    @Test
    public void whenCommandHasMinDiskThenReturnMinDisk() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .minDisk(1)
                .build();

        Assertions.assertEquals(1, command.getMinDisk());
    }

    @Test
    public void whenCommandHasUsernameThenReturnUsername() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .username("username")
                .build();

        Assertions.assertEquals("username", command.getUsername());
    }

    @Test
    public void whenCommandHasStatusThenReturnStatus() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .status("status")
                .build();

        Assertions.assertEquals("status", command.getStatus());
    }

    @Test
    public void whenCommandHasVisibilityThenReturnVisibility() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
               .visibility("visibility")
                .build();

        Assertions.assertEquals("visibility", command.getVisibility());
    }

    @Test
    public void whenCommandToString() {

        ImageUpdateCommand command = imageUpdateCommand();

        String toString = "ImageUpdateCommand(id=c050327c-f03d-4ff6-8d8b-25e3d7a22542, ovhId=ovhId, type=type, imageCreatedDate=2020-11-03T23:00, flavorType=flavor-type, hourly=hourly, monthly=monthly, size=1.0, minRam=1, minDisk=1, username=username, status=status, visibility=visibility)";

        Assertions.assertEquals(toString, command.toString());
    }

    @Test
    public void whenCommandToHashcode() {

        ImageUpdateCommand command = imageUpdateCommand();

        Assertions.assertEquals(-773337834, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        ImageUpdateCommand command1 = imageUpdateCommand();
        ImageUpdateCommand command2 = imageUpdateCommand();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        ImageUpdateCommand command = imageUpdateCommand();

        Assertions.assertNotEquals(command, ImageUpdateCommand.builder().build());
    }

    private ImageUpdateCommand imageUpdateCommand() {

        return ImageUpdateCommand.builder()
                .id(UUID.fromString("c050327c-f03d-4ff6-8d8b-25e3d7a22542"))
                .ovhId("ovhId")
                .type("type")
                .imageCreatedDate(LocalDateTime.of(2020, 11, 3, 23, 0))
                .flavorType("flavor-type")
                .hourly("hourly")
                .monthly("monthly")
                .size(1.0D)
                .minRam(1)
                .minDisk(1)
                .username("username")
                .status("status")
                .visibility("visibility")
                .build();
    }
}
