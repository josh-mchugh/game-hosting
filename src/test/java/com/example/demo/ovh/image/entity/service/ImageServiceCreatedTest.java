package com.example.demo.ovh.image.entity.service;

import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageServiceCreatedTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private Region region;

    @BeforeEach
    public void setup() {

        region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();
    }

    @Test
    public void whenCreateIsValidThenReturnNonNull() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("cefc8220-ba0a-4327-b13d-591abaf4be0c")
                .name("Ubuntu 20.04")
                .imageCreatedDate(LocalDateTime.of(2020, 4, 24, 9, 12, 57))
                .flavorType(null)
                .minDisk(0)
                .minRam(0)
                .monthly(null)
                .hourly(null)
                .type("linux")
                .username("ubuntu")
                .status("active")
                .visibility("public")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertNotNull(image);
    }

    @Test
    public void whenCreateHasNullParamThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> imageService.handleCreated(null));
    }

    @Test
    public void whenCreateHasIdThenExpectId() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertNotNull(image.getId());
    }

    @Test
    public void whenCreateHasNullIdThenExpectException() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(null)
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> imageService.handleCreated(event));
    }

    @Test
    public void whenCreateHasRegionNameThenExpectNotNull() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertNotNull(image);
    }

    @Test
    public void whenCreateHasNullRegionNameThenThrowException() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(null)
                .imageId("image-id")
                .name("name")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> imageService.handleCreated(event));
    }

    @Test
    public void whenCreateHasImageIdThenExpectImageId() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("image-id", image.getImageId());
    }

    @Test
    public void whenCreateHasNullImageIdThenThrowException() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId(null)
                .name("name")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> imageService.handleCreated(event));
    }

    @Test
    public void whenCreateHasNameThenReturnName() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("name", image.getName());
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> imageService.handleCreated(event));
    }

    @Test
    public void whenCreateHasTypeThenReturnType() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .type("type")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("type", image.getType());
    }

    @Test
    public void whenCreateHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .imageCreatedDate(createdDate)
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals(createdDate, image.getImageCreatedDate());
    }

    @Test
    public void whenCreateHasFlavorTypeThenReturnFlavorType() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .flavorType("flavor-type")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("flavor-type", image.getFlavorType());
    }

    @Test
    public void whenCreateHasHourlyThenReturnHourly() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .hourly("hourly")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("hourly", image.getHourly());
    }

    @Test
    public void whenCreateHasMonthlyThenReturnMonthly() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .monthly("monthly")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("monthly", image.getMonthly());
    }

    @Test
    public void whenCreateHasSizeThenReturnSize() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .size(1.0D)
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals(1.0D, image.getSize());
    }

    @Test
    public void whenCreateHasMinRamThenReturnMinRam() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .minRam(1)
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals(1, image.getMinRam());
    }

    @Test
    public void whenCreateHasMinDiskThenReturnMinDisk() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .minDisk(1)
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals(1, image.getMinDisk());
    }

    @Test
    public void whenCreateHasUsernameThenReturnUsername() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .username("username")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("username", image.getUsername());
    }

    @Test
    public void whenCreateHasStatusThenReturnStatus() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .status("status")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("status", image.getStatus());
    }

    @Test
    public void whenCreateHasVisibilityThenReturnVisibility() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .visibility("visibility")
                .build();

        Image image = imageService.handleCreated(event);

        Assertions.assertEquals("visibility", image.getVisibility());
    }
}
