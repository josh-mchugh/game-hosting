package com.example.demo.ovh.image.service;

import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.image.service.model.ImageUpdateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestImageCreateRequest;
import com.example.demo.sample.util.TestRegionCreateRequest;
import com.google.common.collect.ImmutableList;
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
public class ImageServiceTest {

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
    public void testExistAllShouldBeFalse() {

        boolean exists = imageService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void testExistAllShouldBeTrue() {

        ImageCreateRequest imageCreateRequest = TestImageCreateRequest.builder(TestImageCreateRequest.Type.DEBIAN_8_GITLAB)
                .imageId("exists-all-be-true")
                .regionName(region.getName())
                .build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        boolean exists = imageService.existsAny();

        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByNameShouldBeTrue() {

        ImageCreateRequest imageCreateRequest = TestImageCreateRequest.builder(TestImageCreateRequest.Type.DEBIAN_8_GITLAB)
                .name("Test Name")
                .regionName(region.getName())
                .build();
        imageService.handleImageCreate(imageCreateRequest);

        boolean exists = imageService.existsByName("Test Name");

        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByNameShouldBeFalse() {

        boolean exists = imageService.existsByName("Test Name");

        Assertions.assertFalse(exists);
    }

    @Test
    public void testHandleImageCreate() {

        ImageCreateRequest imageCreateRequest = TestImageCreateRequest.builder(TestImageCreateRequest.Type.DEBIAN_8_GITLAB)
                .regionName(region.getName())
                .build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        Assertions.assertNotNull(image.getId());
        Assertions.assertEquals(imageCreateRequest.getImageId(), image.getImageId());
        Assertions.assertEquals(imageCreateRequest.getName(), image.getName());
        Assertions.assertEquals(imageCreateRequest.getType(), image.getType());
        Assertions.assertEquals(imageCreateRequest.getFlavorType(), image.getFlavorType());
        Assertions.assertNull(image.getHourly());
        Assertions.assertNull(image.getMonthly());
        Assertions.assertEquals(imageCreateRequest.getSize(), image.getSize());
        Assertions.assertEquals(imageCreateRequest.getMinRam(), image.getMinRam());
        Assertions.assertEquals(imageCreateRequest.getMinDisk(), image.getMinDisk());
        Assertions.assertEquals(imageCreateRequest.getUsername(), image.getUsername());
        Assertions.assertEquals(imageCreateRequest.getStatus(), image.getStatus());
        Assertions.assertEquals(imageCreateRequest.getVisibility(), image.getVisibility());
    }

    @Test
    public void testHandleImageUpdate() {

        ImageCreateRequest imageCreateRequest = TestImageCreateRequest.builder(TestImageCreateRequest.Type.DEBIAN_8_GITLAB)
                .regionName(region.getName())
                .build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        RegionCreateRequest updateRegionCreateRequest = TestRegionCreateRequest.builder(TestRegionCreateRequest.Type.US_EAST_VA).build();
        Region updatedRegion = regionService.handleRegionCreate(updateRegionCreateRequest);

        ImageUpdateRequest imageUpdateRequest = ImageUpdateRequest.builder()
                .imageId("new-image-id")
                .regionName(updatedRegion.getName())
                .name(image.getName())
                .imageCreatedDate(LocalDateTime.now())
                .flavorType(null)
                .hourly("updated.hourly")
                .monthly("updated.monthly")
                .size(3.14)
                .minRam(10)
                .minDisk(10)
                .username("test")
                .tags(ImmutableList.of("tag1", "tag2"))
                .status("inactive")
                .visibility(null)
                .build();
        Image updatedImage = imageService.handleImageUpdate(imageUpdateRequest);

        Assertions.assertEquals(image.getId(), updatedImage.getId());
        Assertions.assertEquals(image.getName(), updatedImage.getName());

        Assertions.assertNotEquals(image.getImageId(), updatedImage.getImageId());
        Assertions.assertNotEquals(image.getImageCreatedDate(), updatedImage.getImageCreatedDate());
        Assertions.assertNotEquals(image.getHourly(), updatedImage.getHourly());
        Assertions.assertNotEquals(image.getMonthly(), updatedImage.getMonthly());
        Assertions.assertNotEquals(image.getSize(), updatedImage.getSize());
        Assertions.assertNotEquals(image.getMinRam(), updatedImage.getMinRam());
        Assertions.assertNotEquals(image.getMinDisk(), updatedImage.getMinDisk());
        Assertions.assertNotEquals(image.getUsername(), updatedImage.getUsername());
        Assertions.assertNotEquals(image.getStatus(), updatedImage.getStatus());
        Assertions.assertNotEquals(image.getVisibility(), updatedImage.getVisibility());

        Assertions.assertEquals(imageUpdateRequest.getName(), updatedImage.getName());
        Assertions.assertEquals(imageUpdateRequest.getImageCreatedDate(), updatedImage.getImageCreatedDate());
        Assertions.assertEquals(imageUpdateRequest.getHourly(), updatedImage.getHourly());
        Assertions.assertEquals(imageUpdateRequest.getMonthly(), updatedImage.getMonthly());
        Assertions.assertEquals(imageUpdateRequest.getSize(), updatedImage.getSize());
        Assertions.assertEquals(imageUpdateRequest.getMinRam(), updatedImage.getMinRam());
        Assertions.assertEquals(imageUpdateRequest.getMinDisk(), updatedImage.getMinDisk());
        Assertions.assertEquals(imageUpdateRequest.getUsername(), updatedImage.getUsername());
        Assertions.assertEquals(imageUpdateRequest.getStatus(), updatedImage.getStatus());
        Assertions.assertEquals(imageUpdateRequest.getVisibility(), updatedImage.getVisibility());
    }
}
