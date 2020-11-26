package com.example.demo.user.scheduler;

import com.example.demo.user.scheduler.service.IRecoveryTokenSchedulerService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecoveryTokenScheduler {

    private final IRecoveryTokenSchedulerService recoveryTokenSchedulerService;

    @Scheduled(fixedDelayString = "${app.password.recovery-scheduler-delay}", initialDelayString = "${app.password.recovery-scheduler-initial-delay}")
    public void scheduledExpiredRecoveryTokenProcessor() {

        log.info("Processing Expired RecoveryTokens");

        ImmutableList<Object> users = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        log.info("Processed Expired Tokens: {}", users.size());
        log.info("FinishedProcessing Expired Recovery Tokens");
    }
}
