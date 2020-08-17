package com.example.demo.ovh.region.scheduler;

import com.example.demo.ovh.region.scheduler.service.IRegionSchedulerService;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionScheduler {

    private final IRegionSchedulerService regionSchedulerService;

    @Scheduled(fixedDelayString = "${app.ovh.region-scheduler-delay}", initialDelayString = "${app.ovh.region-scheduler-initial-delay}")
    public void scheduledRegionUpdater() {

        log.info("Start: Regions Updates");

        ImmutableList<String> regionNames = regionSchedulerService.getRegionNames();

        log.info("Retrieved Regions: {}", CollectionUtils.size(regionNames));

        ProcessRegionResponse response = regionSchedulerService.processRegions(regionNames);

        log.info("Created Regions: {}", CollectionUtils.size(response.getCreatedRegions()));
        log.info("Updated Regions: {}", CollectionUtils.size(response.getUpdatedRegions()));

        log.info("Finished: Regions Updates");
    }
}
