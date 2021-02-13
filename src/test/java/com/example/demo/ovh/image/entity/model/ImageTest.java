package com.example.demo.ovh.image.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageTest {

    @Test
    public void whenImageHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        Image image = Image.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, image.getId());
    }

    @Test
    public void whenImageHasImageIdThenImageId() {

        Image image = Image.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", image.getOvhId());
    }

    @Test
    public void whenImageHasNameThenReturnName() {

        Image image = Image.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", image.getName());
    }

    @Test
    public void whenImageHasTypeThenReturnType() {

        Image image = Image.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", image.getType());
    }

    @Test
    public void whenImageHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        Image image = Image.builder()
                .imageCreatedDate(createdDate)
                .build();

        Assertions.assertEquals(createdDate, image.getImageCreatedDate());
    }

    @Test
    public void whenImageHasFlavorTypeThenReturnFlavorType() {

        Image image = Image.builder()
                .flavorType("flavor-type")
                .build();

        Assertions.assertEquals("flavor-type", image.getFlavorType());
    }

    @Test
    public void whenImageHasHourlyThenReturnHourly() {

        Image image = Image.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", image.getHourly());
    }

    @Test
    public void whenImageHasMonthlyThenReturnMonthly() {

        Image image = Image.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", image.getMonthly());
    }

    @Test
    public void whenImageHasSizeThenReturnSize() {

        Image image = Image.builder()
                .size(1.0D)
                .build();

        Assertions.assertEquals(1.0D, image.getSize());
    }

    @Test
    public void whenImageHasMinRamThenReturnMinRam() {

        Image image = Image.builder()
                .minRam(1)
                .build();

        Assertions.assertEquals(1, image.getMinRam());
    }

    @Test
    public void whenImageHasMinDiskThenReturnMinDisk() {

        Image image = Image.builder()
                .minDisk(1)
                .build();

        Assertions.assertEquals(1, image.getMinDisk());
    }

    @Test
    public void whenImageHasUsernameThenReturnUsername() {

        Image image = Image.builder()
                .username("username")
                .build();

        Assertions.assertEquals("username", image.getUsername());
    }

    @Test
    public void whenImageHasStatusThenReturnStatus() {

        Image image = Image.builder()
                .status("status")
                .build();

        Assertions.assertEquals("status", image.getStatus());
    }

    @Test
    public void whenImageHasVisibilityThenReturnVisibility() {

        Image image = Image.builder()
                .visibility("visibility")
                .build();

        Assertions.assertEquals("visibility", image.getVisibility());
    }

    @Test
    public void whenImageToString() {

        Image image = image();

        String expected = "Image(id=1376d42e-c6fd-47a6-9642-1626fc07e7c8, ovhId=ovhId, name=name, type=type, imageCreatedDate=2020-11-04T09:36, flavorType=flavor-type, hourly=hourly, monthly=monthly, size=1.0, minRam=1, minDisk=1, username=username, status=status, visibility=visibility)";

        Assertions.assertEquals(expected, image.toString());
    }

    @Test
    public void whenImageHashCode() {

        Image image = image();

        Assertions.assertEquals(947312371, image.hashCode());
    }

    @Test
    public void whenImageEquals() {

        Image image1 = image();
        Image image2 = image();

        Assertions.assertEquals(image1, image2);
    }

    @Test
    public void whenImageNotEquals() {

        Image image = image();

        Assertions.assertNotEquals(image, Image.builder().build());
    }

    private Image image() {

        return Image.builder()
                .id(UUID.fromString("1376d42e-c6fd-47a6-9642-1626fc07e7c8"))
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .imageCreatedDate(LocalDateTime.of(2020, 11, 4, 9, 36))
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
