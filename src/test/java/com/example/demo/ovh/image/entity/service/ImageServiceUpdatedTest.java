package com.example.demo.ovh.image.entity.service;

import com.example.demo.ovh.image.aggregate.event.ImageUpdatedEvent;
import com.example.demo.ovh.image.entity.model.Image;
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

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageServiceUpdatedTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private Image image;

    @BeforeEach
    public void setup() {

        SampleData data = sampleBuilder.builder()
                .region()
                .image()
                .build();

        image = data.getImage();
    }

    @Test
    public void whenUpdateIsValidThenExpectNonNull() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
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
                .id(image.getId())
                .ovhId("ovhId")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertNotNull(image.getId());
    }

    @Test
    public void whenUpdateNullIdThenThrowException() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(null)
                .ovhId("ovhId")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> imageService.handleUpdated(event));
    }

    @Test
    public void whenUpdateHasRegionNameThenReturnNonNull() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertNotNull(image);
    }

    @Test
    public void whenUpdateHasOvhIdThenReturnOvhId() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("newOvhId")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("newOvhId", image.getOvhId());
    }

    @Test
    public void whenUpdateHasNameThenReturnName() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(image.getName(), image.getName());
    }

    @Test
    public void whenUpdateHasTypeThenReturnType() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .type("new type")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new type", image.getType());
    }

    @Test
    public void whenUpdateHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .imageCreatedDate(createdDate)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(createdDate, image.getImageCreatedDate());
    }

    @Test
    public void whenUpdateHasFlavorTypeThenReturnFlavorType() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .flavorType("new flavor type")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new flavor type", image.getFlavorType());
    }

    @Test
    public void whenUpdateHasHourlyThenReturnHourly() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .hourly("new hourly")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new hourly", image.getHourly());
    }

    @Test
    public void whenUpdateHasMonthlyThenReturnMonthly() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .monthly("new monthly")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new monthly", image.getMonthly());
    }

    @Test
    public void whenUpdateHasSizeThenReturnSize() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .size(3.14D)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(3.14D, image.getSize());
    }

    @Test
    public void whenUpdateHasMinRamThenReturnMinRam() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .minRam(99)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(99, image.getMinRam());
    }

    @Test
    public void whenUpdateHasMinDiskThenReturnMinDisk() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .minDisk(99)
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals(99, image.getMinDisk());
    }

    @Test
    public void whenUpdateHasUsernameThenReturnUsername() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .username("new username")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new username", image.getUsername());
    }

    @Test
    public void whenUpdateHasStatusThenReturnStatus() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .status("new status")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new status", image.getStatus());
    }

    @Test
    public void whenUpdateHasVisibilityThenReturnVisibility() {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(image.getId())
                .ovhId("ovhId")
                .visibility("new visibility")
                .build();

        Image image = imageService.handleUpdated(event);

        Assertions.assertEquals("new visibility", image.getVisibility());
    }
}
