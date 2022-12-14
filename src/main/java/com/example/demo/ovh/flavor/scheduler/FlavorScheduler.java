package com.example.demo.ovh.flavor.scheduler;

import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.scheduler.service.FlavorSchedulerService;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
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
public class FlavorScheduler {

    private final FlavorSchedulerService flavorSchedulerService;

    @Scheduled(fixedDelayString = "${ovh.flavor-scheduler-delay}", initialDelayString = "${ovh.flavor-scheduler-initial-delay}")
    public void scheduledFlavorUpdater() throws ExecutionException, InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();

        ImmutableList<FlavorApi> flavorResponses = flavorSchedulerService.getFlavorResponses();
        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(flavorResponses);

        LocalDateTime stopTime = LocalDateTime.now();

        log.info("Ovh Flavors | Stats - Total: {}, Created: {}, Updated: {} | Time - Start Time: {}, End Time: {}, Elapsed: {}ms",
                CollectionUtils.size(flavorResponses),
                CollectionUtils.size(response.getCreatedFlavors()),
                CollectionUtils.size(response.getUpdatedFlavors()),
                startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                stopTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                Duration.between(startTime, stopTime).toMillis()
        );
    }
}
