package com.example.demo.user.scheduler;

import com.example.demo.user.scheduler.service.RecoveryTokenSchedulerService;
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
public class RecoveryTokenScheduler {

    private final RecoveryTokenSchedulerService recoveryTokenSchedulerService;

    @Scheduled(fixedDelayString = "${app.password.recovery-scheduler-delay}", initialDelayString = "${app.password.recovery-scheduler-initial-delay}")
    public void scheduledExpiredRecoveryTokenProcessor() throws ExecutionException, InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();

        ImmutableList<Object> users = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        LocalDateTime stopTime = LocalDateTime.now();

        log.info("Expired User Tokens | Stats - Total: {} | Time - Start Time: {}, End Time: {}, Elapsed: {}ms",
                CollectionUtils.size(users),
                startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                stopTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                Duration.between(startTime, stopTime).toMillis()
        );
    }
}
