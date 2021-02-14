package com.example.demo.ovh.image.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasRegionIdThenReturnRegionId() {

        UUID regionId = UUID.randomUUID();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .regionId(regionId)
                .build();

        Assertions.assertEquals(regionId, event.getRegionId());
    }

    @Test
    public void whenEventHasImageIdThenReturnImageId() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", event.getOvhId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", event.getType());
    }

    @Test
    public void whenEventHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .imageCreatedDate(createdDate)
                .build();

        Assertions.assertEquals(createdDate, event.getImageCreatedDate());
    }

    @Test
    public void whenEventHasFlavorTypeThenReturnFlavorType() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .flavorType("flavor-type")
                .build();

        Assertions.assertEquals("flavor-type", event.getFlavorType());
    }

    @Test
    public void whenEventHasHourlyThenReturnHourly() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", event.getHourly());
    }

    @Test
    public void whenEventHasMonthlyThenReturnMonthly() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", event.getMonthly());
    }

    @Test
    public void whenEventHasSizeThenReturnSize() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .size(1.0D)
                .build();

        Assertions.assertEquals(1.0D, event.getSize());
    }

    @Test
    public void whenEventHasMinRamThenReturnMinRam() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .minRam(1)
                .build();

        Assertions.assertEquals(1, event.getMinRam());
    }

    @Test
    public void whenEventHasMinDiskThenReturnMinDisk() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .minDisk(1)
                .build();

        Assertions.assertEquals(1, event.getMinDisk());
    }

    @Test
    public void whenEventHasUsernameThenReturnUsername() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .username("username")
                .build();

        Assertions.assertEquals("username", event.getUsername());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .status("status")
                .build();

        Assertions.assertEquals("status", event.getStatus());
    }

    @Test
    public void whenEventHasVisibilityThenReturnVisibility() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .visibility("visibility")
                .build();

        Assertions.assertEquals("visibility", event.getVisibility());
    }

    @Test
    public void whenEventToString() {

        ImageCreatedEvent event = event();

        String toString = "ImageCreatedEvent(id=6c37d281-4e5c-4c15-aa4e-4efd62f2e8d2, regionId=4cfaf14f-f1fe-4fc7-9821-b1a3f2ac8e3c, ovhId=ovhId, name=name, type=type, imageCreatedDate=2020-11-04T13:08, flavorType=flavor-type, hourly=hourly, monthly=monthly, size=1.0, minRam=null, minDisk=1, username=username, status=status, visibility=visibility)";

        Assertions.assertEquals(toString, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        ImageCreatedEvent event = event();

        Assertions.assertEquals(-57256589, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        ImageCreatedEvent event1 = event();
        ImageCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        ImageCreatedEvent event = event();

        Assertions.assertNotEquals(event, ImageCreatedEvent.builder().build());
    }

    private ImageCreatedEvent event() {

        return ImageCreatedEvent.builder()
                .id(UUID.fromString("6c37d281-4e5c-4c15-aa4e-4efd62f2e8d2"))
                .regionId(UUID.fromString("4cfaf14f-f1fe-4fc7-9821-b1a3f2ac8e3c"))
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .imageCreatedDate(LocalDateTime.of(2020, 11, 4, 13,8))
                .flavorType("flavor-type")
                .hourly("hourly")
                .monthly("monthly")
                .size(1.0D)
                .minDisk(1)
                .minDisk(1)
                .username("username")
                .status("status")
                .visibility("visibility")
                .build();
    }
}
