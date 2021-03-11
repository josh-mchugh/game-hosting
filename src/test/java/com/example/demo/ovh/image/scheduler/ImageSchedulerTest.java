package com.example.demo.ovh.image.scheduler;

import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.service.IImageSchedulerService;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ExecutionException;

public class ImageSchedulerTest {

    @Test
    public void whenImageSchedulerHasNoApiResponseThrowNoExceptions() throws ExecutionException, InterruptedException {

        IImageSchedulerService imageSchedulerService = Mockito.mock(IImageSchedulerService.class);
        ProcessedImagesResponse response = ProcessedImagesResponse.builder().build();

        Mockito.when(imageSchedulerService.getImageResponses()).thenReturn(ImmutableList.of());
        Mockito.when(imageSchedulerService.processScheduledImages(Mockito.any())).thenReturn(response);

        ImageScheduler imageScheduler = new ImageScheduler(imageSchedulerService);

        Assertions.assertDoesNotThrow(imageScheduler::scheduledImageUpdater);
    }

    @Test
    public void whenImageSchedulerHasApiResponseThenThrowNoExceptions() throws ExecutionException, InterruptedException {

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
    public void whenImageSchedulerHasNullApiThenThrowNoExceptions() throws ExecutionException, InterruptedException {

        IImageSchedulerService imageSchedulerService = Mockito.mock(IImageSchedulerService.class);
        ProcessedImagesResponse response = ProcessedImagesResponse.builder().build();

        Mockito.when(imageSchedulerService.getImageResponses()).thenReturn(null);
        Mockito.when(imageSchedulerService.processScheduledImages(Mockito.any())).thenReturn(response);

        ImageScheduler imageScheduler = new ImageScheduler(imageSchedulerService);

        Assertions.assertDoesNotThrow(imageScheduler::scheduledImageUpdater);
    }

    @Test
    public void whenImageSchedulerHasNullResponseThenThrowException() throws ExecutionException, InterruptedException {

        IImageSchedulerService imageSchedulerService = Mockito.mock(IImageSchedulerService.class);

        Mockito.when(imageSchedulerService.getImageResponses()).thenReturn(ImmutableList.of(new ImageApi()));
        Mockito.when(imageSchedulerService.processScheduledImages(Mockito.any())).thenReturn(null);

        ImageScheduler imageScheduler = new ImageScheduler(imageSchedulerService);

        Assertions.assertThrows(NullPointerException.class, imageScheduler::scheduledImageUpdater);
    }
}
