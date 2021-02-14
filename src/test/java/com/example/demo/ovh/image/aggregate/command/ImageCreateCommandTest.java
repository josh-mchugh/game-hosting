package com.example.demo.ovh.image.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasRegionIdThenReturnRegionId() {

        UUID regionId = UUID.randomUUID();

        ImageCreateCommand command = ImageCreateCommand.builder()
                .regionId(regionId)
                .build();

        Assertions.assertEquals(regionId, command.getRegionId());
    }

    @Test
    public void whenCommandHasImageIdThenReturnImageId() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", command.getOvhId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", command.getType());
    }

    @Test
    public void whenCommandHasImageCreateDateThenReturnImageCreateDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageCreateCommand command = ImageCreateCommand.builder()
                .imageCreatedDate(createdDate)
                .build();

        Assertions.assertEquals(createdDate, command.getImageCreatedDate());
    }

    @Test
    public void whenCommandHasFlavorTypeThenReturnFlavorType() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .flavorType("flavor-type")
                .build();

        Assertions.assertEquals("flavor-type", command.getFlavorType());
    }

    @Test
    public void whenCommandHasHourlyThenReturnHourly() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", command.getHourly());
    }

    @Test
    public void whenCommandHasMonthlyThenReturnMonthly() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", command.getMonthly());
    }

    @Test
    public void whenCommandHasSizeThenReturnSize() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .size(1.0D)
                .build();

        Assertions.assertEquals(1.0D, command.getSize());
    }

    @Test
    public void whenCommandHasMinRamThenReturnMinRam() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .minRam(1)
                .build();

        Assertions.assertEquals(1, command.getMinRam());
    }

    @Test
    public void whenCommandHasMinDiskThenReturnMinDisk() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .minDisk(1)
                .build();

        Assertions.assertEquals(1, command.getMinDisk());
    }

    @Test
    public void whenCommandHasUsernameThenReturnUsername() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .username("username")
                .build();

        Assertions.assertEquals("username", command.getUsername());
    }

    @Test
    public void whenCommandHasStatusThenReturnStatus() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .status("status")
                .build();

        Assertions.assertEquals("status", command.getStatus());
    }

    @Test
    public void whenCommandHasVisibilityThenReturnVisibility() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .visibility("visibility")
                .build();

        Assertions.assertEquals("visibility", command.getVisibility());
    }

    @Test
    public void whenCommandToString() {

        ImageCreateCommand imageCreateCommand = command();

        String toString = "ImageCreateCommand(id=cbe91fcc-b7ec-4310-8852-9b444fe9526b, regionId=fcf5b59e-0845-40f1-b924-8b89562c9403, ovhId=ovhId, name=name, type=type, imageCreatedDate=2020-11-03T21:31, flavorType=flavor-type, hourly=hourly, monthly=monthly, size=1.0, minRam=1, minDisk=1, username=username, status=status, visibility=visibility)";

        Assertions.assertEquals(toString, imageCreateCommand.toString());
    }

    @Test
    public void whenCommandHashcode() {

        ImageCreateCommand imageCreateCommand = command();

        Assertions.assertEquals(145994591, imageCreateCommand.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        ImageCreateCommand imageCreateCommand1 = command();

        ImageCreateCommand imageCreateCommand2 = command();

        Assertions.assertEquals(imageCreateCommand1, imageCreateCommand2);
    }

    @Test
    public void whenCommandNotEquals() {

        ImageCreateCommand imageCreateCommand = command();

        Assertions.assertNotEquals(imageCreateCommand, ImageCreateCommand.builder().build());
    }

    private ImageCreateCommand command() {

        return ImageCreateCommand.builder()
                .id(UUID.fromString("cbe91fcc-b7ec-4310-8852-9b444fe9526b"))
                .regionId(UUID.fromString("fcf5b59e-0845-40f1-b924-8b89562c9403"))
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .imageCreatedDate(LocalDateTime.of(2020, 11, 3, 21, 31))
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
