package com.example.demo.ovh.flavor.scheduler;

import com.example.demo.ovh.feign.model.OvhFlavorApiResponse;
import com.example.demo.ovh.flavor.scheduler.service.IFlavorSchedulerService;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlavorScheduler {

    private final IFlavorSchedulerService flavorSchedulerService;

    @Scheduled(fixedDelayString = "${app.ovh.flavor-scheduler-delay}", initialDelayString = "${app.ovh.flavor-scheduler-initial-delay}")
    public void scheduledFlavorUpdater() {

        log.info("Start: Flavor Updates");

        ImmutableList<OvhFlavorApiResponse> flavorResponses = flavorSchedulerService.getFlavorResponses();

        log.info("Retrieved Flavors: {}", CollectionUtils.size(flavorResponses));

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(flavorResponses);

        log.info("Crated created: {}", CollectionUtils.size(response.getCreatedFlavors()));
        log.info("Updated Flavors: {}", CollectionUtils.size(response.getUpdatedFlavors()));

        log.info("Finished: Flavor Updates");
    }
}
