package com.example.demo.ovh.image.scheduler;

import com.example.demo.ovh.instance.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.service.IImageSchedulerService;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ImageSchedulerTest {

    @Test
    public void whenImageSchedulerHasNoApiResponseThrowNoExceptions() {

        IImageSchedulerService imageSchedulerService = Mockito.mock(IImageSchedulerService.class);
        ProcessedImagesResponse response = ProcessedImagesResponse.builder().build();

        Mockito.when(imageSchedulerService.getImageResponses()).thenReturn(ImmutableList.of());
        Mockito.when(imageSchedulerService.processScheduledImages(Mockito.any())).thenReturn(response);

        ImageScheduler imageScheduler = new ImageScheduler(imageSchedulerService);

        Assertions.assertDoesNotThrow(imageScheduler::scheduledImageUpdater);
    }

    @Test
    public void whenImageSchedulerHasApiResponseThenThrowNoExceptions() {

        IImageSchedulerService imageSchedulerService = Mockito.mock(IImageSchedulerService.class);
        ProcessedImagesResponse response = ProcessedImagesResponse.builder()
                .createdImage("id")
                .updatedImage("id")
                .build();

        Mockito.when(imageSchedulerService.getImageResponses()).thenReturn(ImmutableList.of(new ImageApi(), new ImageApi()));
        Mockito.when(imageSchedulerService.processScheduledImages(Mockito.any())).thenReturn(response);

        ImageScheduler imageScheduler = new ImageScheduler(imageSchedulerService);

        Assertions.assertDoesNotThrow(imageScheduler::scheduledImageUpdater);
    }

    @Test
    public void whenImageSchedulerHasNullApiThenThrowNoExceptions() {

        IImageSchedulerService imageSchedulerService = Mockito.mock(IImageSchedulerService.class);
        ProcessedImagesResponse response = ProcessedImagesResponse.builder().build();

        Mockito.when(imageSchedulerService.getImageResponses()).thenReturn(null);
        Mockito.when(imageSchedulerService.processScheduledImages(Mockito.any())).thenReturn(response);

        ImageScheduler imageScheduler = new ImageScheduler(imageSchedulerService);

        Assertions.assertDoesNotThrow(imageScheduler::scheduledImageUpdater);
    }

    @Test
    public void whenImageSchedulerHasNullResponseThenThrowException() {

        IImageSchedulerService imageSchedulerService = Mockito.mock(IImageSchedulerService.class);

        Mockito.when(imageSchedulerService.getImageResponses()).thenReturn(ImmutableList.of(new ImageApi()));
        Mockito.when(imageSchedulerService.processScheduledImages(Mockito.any())).thenReturn(null);

        ImageScheduler imageScheduler = new ImageScheduler(imageSchedulerService);

        Assertions.assertThrows(NullPointerException.class, imageScheduler::scheduledImageUpdater);
    }
}
