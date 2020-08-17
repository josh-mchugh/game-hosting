package com.example.demo.ovh.image;

import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.image.service.model.ImageUpdateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.sample.TestImageUtil;
import com.example.demo.sample.TestRegionUtil;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageServiceTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IRegionService regionService;

    @Test
    @Order(1)
    public void testExistAllShouldBeFalse() {

        boolean exists = imageService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void testExistAllShouldBeTrue() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("exists-all-be-true");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.DEBIAN_8_GITLAB)
                .imageId("exists-all-be-true")
                .regionName(region.getName())
                .build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        boolean exists = imageService.existsAny();

        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByImageIdShouldBeTrue() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("exits-by-image-id-be-true");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.DEBIAN_8_GITLAB)
                .imageId("exists-by-image-id-be-true")
                .regionName(region.getName())
                .build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        boolean exists = imageService.existsByImageId(image.getImageId());

        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByImageIdShouldBeFalse() {

        boolean exists = imageService.existsByImageId("exists-by-image-id-be-false");

        Assertions.assertFalse(exists);
    }

    @Test
    public void testHandleImageCreate() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("US-EAST-VA-1");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.DEBIAN_8_GITLAB)
                .imageId("handle-image-create")
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

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("handle-image-update");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.DEBIAN_8_GITLAB)
                .imageId("handle-image-update")
                .regionName(region.getName())
                .build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        RegionCreateRequest updateRegionCreateRequest = TestRegionUtil.createRegion("handle-image-region-updated");
        Region updatedRegion = regionService.handleRegionCreate(updateRegionCreateRequest);

        ImageUpdateRequest imageUpdateRequest = ImageUpdateRequest.builder()
                .imageId(image.getImageId())
                .regionName(updatedRegion.getName())
                .name("handle-image-region-updated")
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
        Assertions.assertEquals(image.getImageId(), updatedImage.getImageId());

        Assertions.assertNotEquals(image.getName(), updatedImage.getName());
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
