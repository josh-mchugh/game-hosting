package com.example.demo.ovh.image;

import com.example.demo.ovh.feign.image.ImageClient;
import com.example.demo.ovh.feign.image.model.ImageApi;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.scheduler.service.IImageSchedulerService;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.sample.TestImageUtil;
import com.example.demo.sample.TestRegionUtil;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageSchedulerServiceTest {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IImageSchedulerService imageSchedulerService;

    @MockBean
    private ImageClient imageClient;

    @Test
    public void testGetImageResponsesCreated() {

        ImageApi imageResponse = new ImageApi();
        imageResponse.setImageId("get-image-responses");

        Mockito.when(imageClient.getImages(Mockito.anyString())).thenReturn(ImmutableList.of(imageResponse));

        ImmutableList<ImageApi> imageResponses = imageSchedulerService.getImageResponses();

        Assertions.assertEquals(1, imageResponses.size());
        Assertions.assertEquals(imageResponse.getImageId(), imageResponses.get(0).getImageId());
    }

    @Test
    public void testProcessScheduledImagesUpdated() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("process-scheduled-images-updated");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        ImageCreateRequest imageCreateRequest = TestImageUtil.builder(TestImageUtil.Type.DEBIAN_8_GITLAB)
                .imageId("process-scheduled-images-updated")
                .regionName(region.getName())
                .build();
        Image image = imageService.handleImageCreate(imageCreateRequest);

        ImageApi imageResponse = new ImageApi();
        imageResponse.setImageId(image.getImageId());
        imageResponse.setRegionName(region.getName());
        imageResponse.setName("new-name");

        ProcessedImagesResponse responses = imageSchedulerService.processScheduledImages(ImmutableList.of(imageResponse));

        Assertions.assertEquals(0, CollectionUtils.size(responses.getCreatedImages()));
        Assertions.assertEquals(1, CollectionUtils.size(responses.getUpdatedImages()));

        Assertions.assertEquals(image.getId(), responses.getUpdatedImages().get(0).getId());
        Assertions.assertNotEquals(image.getName(), responses.getUpdatedImages().get(0).getName());
        Assertions.assertEquals(imageResponse.getName(), responses.getUpdatedImages().get(0).getName());
    }

    @Test
    public void testProcessScheduledImagesCreated() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("process-scheduled-image-created");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        ImageApi imageResponse = new ImageApi();
        imageResponse.setImageId("process-scheduled-image-created");
        imageResponse.setRegionName(region.getName());

        ProcessedImagesResponse responses = imageSchedulerService.processScheduledImages(ImmutableList.of(imageResponse));

        Assertions.assertEquals(1, CollectionUtils.size(responses.getCreatedImages()));
        Assertions.assertEquals(0, CollectionUtils.size(responses.getUpdatedImages()));

        Assertions.assertNotNull(responses.getCreatedImages().get(0).getId());
        Assertions.assertEquals(imageResponse.getImageId(), responses.getCreatedImages().get(0).getImageId());
    }
}
