package com.example.demo.ovh.instance.scheduler;

import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.scheduler.service.IInstanceSchedulerService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstanceUpdateScheduler {

    private final IInstanceSchedulerService instanceSchedulerService;

    @Scheduled(fixedDelayString = "${ovh.instance-scheduler-delay}", initialDelayString = "${ovh.instance-scheduler-initial-delay}")
    public void instanceUpdateScheduler() {

        log.info("Processing Instance Updates");

        ImmutableList<Instance> instances = instanceSchedulerService.handleInstanceUpdates();

        log.info("Updated Instances: {}", instances.size());
        log.info("Finished Instance Updates");
    }
}
