package com.example.demo.ovh.instance.scheduler;

import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.scheduler.service.InstanceSchedulerService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstanceUpdateScheduler {

    private final InstanceSchedulerService instanceSchedulerService;

    @Scheduled(fixedDelayString = "${ovh.instance-scheduler-delay}", initialDelayString = "${ovh.instance-scheduler-initial-delay}")
    public void instanceUpdateScheduler() throws ExecutionException, InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();

        ImmutableList<InstanceApi> instanceApis = instanceSchedulerService.getInstanceApis();
        List<UUID> instances = instanceSchedulerService.handleInstanceUpdates(instanceApis);

        LocalDateTime stopTime = LocalDateTime.now();

        log.info("Ovh Instances | Stats - Total: {}, Updated: {} | Time - Start Time: {}, End Time: {}, Elapsed: {}ms",
                CollectionUtils.size(instanceApis),
                CollectionUtils.size(instances),
                startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                stopTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                Duration.between(startTime, stopTime).toMillis()
        );
    }
}
