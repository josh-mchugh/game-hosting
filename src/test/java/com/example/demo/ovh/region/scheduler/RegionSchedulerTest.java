package com.example.demo.ovh.region.scheduler;

import com.example.demo.ovh.region.scheduler.service.RegionSchedulerService;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ExecutionException;

public class RegionSchedulerTest {

    private RegionSchedulerService schedulerService;

    @BeforeEach
    public void setup() {

        schedulerService = Mockito.mock(RegionSchedulerService.class);
    }

    @Test
    public void whenSchedulerExecutesThenExpectNoExceptions() throws ExecutionException, InterruptedException {

        Mockito.when(schedulerService.getRegionNames()).thenReturn(ImmutableList.of());

        ProcessRegionResponse response = ProcessRegionResponse.builder().build();
        Mockito.when(schedulerService.processRegions(Mockito.any())).thenReturn(response);

        RegionScheduler scheduler = new RegionScheduler(schedulerService);

        Assertions.assertDoesNotThrow(scheduler::scheduledRegionUpdater);
    }
}
