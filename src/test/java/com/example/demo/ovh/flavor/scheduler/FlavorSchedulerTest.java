package com.example.demo.ovh.flavor.scheduler;

import com.example.demo.ovh.flavor.scheduler.service.FlavorSchedulerService;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ExecutionException;

public class FlavorSchedulerTest {

    private FlavorSchedulerService flavorSchedulerService;

    @BeforeEach
    public void setup() {

        flavorSchedulerService = Mockito.mock(FlavorSchedulerService.class);
    }

    @Test
    public void whenSchedulerExecutesExpectNoExceptions() throws ExecutionException, InterruptedException {

        Mockito.when(flavorSchedulerService.getFlavorResponses()).thenReturn(null);

        ProcessedFlavorsResponse processedFlavorsResponse = ProcessedFlavorsResponse.builder().build();
        Mockito.when(flavorSchedulerService.processFlavors(Mockito.any())).thenReturn(processedFlavorsResponse);

        FlavorScheduler flavorScheduler = new FlavorScheduler(flavorSchedulerService);

        Assertions.assertDoesNotThrow(flavorScheduler::scheduledFlavorUpdater);
    }
}
