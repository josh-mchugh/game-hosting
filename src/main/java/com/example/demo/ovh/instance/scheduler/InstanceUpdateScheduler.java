package com.example.demo.ovh.instance.scheduler;

import com.example.demo.ovh.instance.scheduler.service.IInstanceSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstanceUpdateScheduler {

    private final IInstanceSchedulerService instanceSchedulerService;

    @Scheduled(fixedDelayString = "${ovh.instance-scheduler-delay}", initialDelayString = "${ovh.instance-scheduler-initial-delay}")
    public void instanceUpdateScheduler() {

        log.info("Processing Instance Updates");

        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates();

        log.info("Updated Instances: {}", instances.size());
        log.info("Finished Instance Updates");
    }
}
