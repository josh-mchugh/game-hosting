package com.example.demo.ovh.image.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageUpdatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasRegionIdThenReturnRegionId() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .regionId("region-id")
                .build();

        Assertions.assertEquals("region-id", event.getRegionId());
    }

    @Test
    public void whenEventHasImageIdThenReturnImageId() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .imageId("image-id")
                .build();

        Assertions.assertEquals("image-id", event.getImageId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", event.getType());
    }

    @Test
    public void whenEventHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .imageCreatedDate(createdDate)
                .build();

        Assertions.assertEquals(createdDate, event.getImageCreatedDate());
    }

    @Test
    public void whenEventHasFlavorTypeThenReturnFlavorType() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .flavorType("flavor-type")
                .build();

        Assertions.assertEquals("flavor-type", event.getFlavorType());
    }

    @Test
    public void whenEventHasHourlyThenReturnHourly() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", event.getHourly());
    }

    @Test
    public void whenEventHasMonthlyThenReturnMonthly() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", event.getMonthly());
    }

    @Test
    public void whenEventHasSizeThenReturnSize() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .size(1.0D)
                .build();

        Assertions.assertEquals(1.0D, event.getSize());
    }

    @Test
    public void whenEventHasMinRamThenReturnMinRam() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .minRam(1)
                .build();

        Assertions.assertEquals(1, event.getMinRam());
    }

    @Test
    public void whenEventHasMinDiskThenReturnMinDisk() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .minDisk(1)
                .build();

        Assertions.assertEquals(1, event.getMinDisk());
    }

    @Test
    public void whenEventHasUsernameThenReturnUsername() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .username("username")
                .build();

        Assertions.assertEquals("username", event.getUsername());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .status("status")
                .build();

        Assertions.assertEquals("status", event.getStatus());
    }

    @Test
    public void whenEventHasVisibilityThenReturnVisibility() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .visibility("visibility")
                .build();

        Assertions.assertEquals("visibility", event.getVisibility());
    }

    @Test
    public void whenEventToString() {

        ImageUpdatedEvent event = imageUpdatedEvent();

        String toString = "ImageUpdatedEvent(id=2ab2fb8d-fa62-4039-9c49-dd89d19631d0, regionId=region-id, imageId=image-id, name=name, type=type, imageCreatedDate=2020-11-04T09:10, flavorType=flavor-type, hourly=hourly, monthly=monthly, size=1.0, minRam=1, minDisk=1, username=username, status=status, visibility=visibility)";

        Assertions.assertEquals(toString, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        ImageUpdatedEvent event = imageUpdatedEvent();

        Assertions.assertEquals(-656212420, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        ImageUpdatedEvent event1 = imageUpdatedEvent();
        ImageUpdatedEvent event2 = imageUpdatedEvent();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        ImageUpdatedEvent event = imageUpdatedEvent();

        Assertions.assertNotEquals(event, ImageUpdatedEvent.builder().build());
    }

    private ImageUpdatedEvent imageUpdatedEvent() {

        return ImageUpdatedEvent.builder()
                .id(UUID.fromString("2ab2fb8d-fa62-4039-9c49-dd89d19631d0"))
                .regionId("region-id")
                .imageId("image-id")
                .name("name")
                .type("type")
                .imageCreatedDate(LocalDateTime.of(2020, 11, 4, 9, 10))
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
