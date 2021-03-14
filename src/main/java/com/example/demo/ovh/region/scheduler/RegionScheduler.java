package com.example.demo.ovh.region.scheduler;

import com.example.demo.ovh.region.scheduler.service.IRegionSchedulerService;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionScheduler {

    private final IRegionSchedulerService regionSchedulerService;

    @Scheduled(fixedDelayString = "${ovh.region-scheduler-delay}", initialDelayString = "${ovh.region-scheduler-initial-delay}")
    public void scheduledRegionUpdater() throws ExecutionException, InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();

        ImmutableList<String> regionNames = regionSchedulerService.getRegionNames();
        ProcessRegionResponse response = regionSchedulerService.processRegions(regionNames);

        LocalDateTime stopTime = LocalDateTime.now();

        log.info("Ovh Regions | Stats - Total: {}, Created: {}, Updated: {} | Time - Start Time: {}, End Time: {}, Elapsed: {}ms",
                CollectionUtils.size(regionNames),
                CollectionUtils.size(response.getCreatedRegions()),
                CollectionUtils.size(response.getUpdatedRegions()),
                startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                stopTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                Duration.between(startTime, stopTime).toMillis()
        );
    }
}
