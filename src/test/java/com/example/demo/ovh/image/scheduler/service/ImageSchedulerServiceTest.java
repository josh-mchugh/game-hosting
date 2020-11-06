package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.entity.service.IImageService;
import com.example.demo.ovh.image.feign.ImageClient;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageSchedulerServiceTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IImageSchedulerService imageSchedulerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private ImageClient imageClient;

    @MockBean
    private CommandGateway commandGateway;

    private Region region;

    @BeforeEach
    public void setup() {

        region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();
    }

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

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .imageId("image-id")
                .name("name")
                .build();
        imageService.handleCreated(event);

        ImageApi imageResponse = new ImageApi();
        imageResponse.setImageId("new-image-id");
        imageResponse.setRegionName(region.getName());
        imageResponse.setName("name");

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessedImagesResponse responses = imageSchedulerService.processScheduledImages(ImmutableList.of(imageResponse));

        Assertions.assertEquals(0, CollectionUtils.size(responses.getCreatedImages()));
        Assertions.assertEquals(1, CollectionUtils.size(responses.getUpdatedImages()));
    }

    @Test
    public void testProcessScheduledImagesCreated() {

        ImageApi imageResponse = new ImageApi();
        imageResponse.setImageId("process-scheduled-image-created");
        imageResponse.setName("name");
        imageResponse.setRegionName(region.getName());

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessedImagesResponse responses = imageSchedulerService.processScheduledImages(ImmutableList.of(imageResponse));

        Assertions.assertEquals(1, CollectionUtils.size(responses.getCreatedImages()));
        Assertions.assertEquals(0, CollectionUtils.size(responses.getUpdatedImages()));
    }
}
