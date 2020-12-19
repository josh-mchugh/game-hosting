package com.example.demo.ovh.image.entity.service;

import com.example.demo.ovh.image.aggregate.event.ImageUpdatedEvent;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageServiceUpdatedTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private Region region;
    private Image image;

    @BeforeEach
    public void setup() {

        SampleData data = sampleBuilder.builder()
                .region()
                .image()
                .build();

        region = data.getRegion();
        image = data.getImage();
    }

    @Test
    public void whenUpdateIsValidThenExpectNonNull() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .imageCreatedDate(LocalDateTime.now())
                .flavorType(null)
                .hourly("updated.hourly")
                .monthly("updated.monthly")
                .size(3.14)
                .minRam(10)
                .minDisk(10)
                .username("test")
                .status("inactive")
                .visibility(null)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertNotNull(image);
    }

    @Test
    public void whenUpdateHasNullParamThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> imageService.handleUpdated(null));
    }

    @Test
    public void whenUpdateHasIdThenReturnId() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertNotNull(image.getId());
    }

    @Test
    public void whenUpdateNullIdThenThrowException() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(null)
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> imageService.handleUpdated(event));
    }

    @Test
    public void whenUpdateHasRegionNameThenReturnNonNull() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertNotNull(image);
    }

    @Test
    public void whenUpdateHasNullRegionNameThenThrowException() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(null)
                .ovhId("ovhId")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> imageService.handleUpdated(event));
    }

    @Test
    public void whenUpdateHasOvhIdThenReturnOvhId() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("newOvhId")
                .name(image.getName())
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("newOvhId", image.getOvhId());
    }

    @Test
    public void whenUpdateHasNameThenReturnName() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(image.getName(), image.getName());
    }

    @Test
    public void whenUpdateHasTypeThenReturnType() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .type("new type")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new type", image.getType());
    }

    @Test
    public void whenUpdateHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .imageCreatedDate(createdDate)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(createdDate, image.getImageCreatedDate());
    }

    @Test
    public void whenUpdateHasFlavorTypeThenReturnFlavorType() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .flavorType("new flavor type")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new flavor type", image.getFlavorType());
    }

    @Test
    public void whenUpdateHasHourlyThenReturnHourly() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .hourly("new hourly")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new hourly", image.getHourly());
    }

    @Test
    public void whenUpdateHasMonthlyThenReturnMonthly() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .monthly("new monthly")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new monthly", image.getMonthly());
    }

    @Test
    public void whenUpdateHasSizeThenReturnSize() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .size(3.14D)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(3.14D, image.getSize());
    }

    @Test
    public void whenUpdateHasMinRamThenReturnMinRam() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .minRam(99)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(99, image.getMinRam());
    }

    @Test
    public void whenUpdateHasMinDiskThenReturnMinDisk() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .minDisk(99)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(99, image.getMinDisk());
    }

    @Test
    public void whenUpdateHasUsernameThenReturnUsername() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .username("new username")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new username", image.getUsername());
    }

    @Test
    public void whenUpdateHasStatusThenReturnStatus() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .status("new status")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new status", image.getStatus());
    }

    @Test
    public void whenUpdateHasVisibilityThenReturnVisibility() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(UUID.fromString(image.getId()))
                .regionId(region.getId())
                .ovhId("ovhId")
                .name(image.getName())
                .visibility("new visibility")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new visibility", image.getVisibility());
    }
}
