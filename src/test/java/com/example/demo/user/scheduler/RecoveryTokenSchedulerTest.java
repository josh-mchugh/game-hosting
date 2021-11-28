package com.example.demo.user.scheduler;

import com.example.demo.user.scheduler.service.RecoveryTokenSchedulerService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class RecoveryTokenSchedulerTest {

    @Test
    public void whenSchedulerProcessesUsers() throws ExecutionException, InterruptedException {

        RecoveryTokenSchedulerService recoveryTokenSchedulerService = Mockito.mock(RecoveryTokenSchedulerService.class);
        Mockito.when(recoveryTokenSchedulerService.processExpiredRecoveryTokens()).thenReturn(ImmutableList.of(UUID.randomUUID()));

        RecoveryTokenScheduler recoveryTokenScheduler = new RecoveryTokenScheduler(recoveryTokenSchedulerService);

        Assertions.assertDoesNotThrow(recoveryTokenScheduler::scheduledExpiredRecoveryTokenProcessor);
    }
    
    @Test
    public void whenSchedulerProcessesNoUsers() throws ExecutionException, InterruptedException {

        RecoveryTokenSchedulerService recoveryTokenSchedulerService = Mockito.mock(RecoveryTokenSchedulerService.class);
        Mockito.when(recoveryTokenSchedulerService.processExpiredRecoveryTokens()).thenReturn(ImmutableList.of());

        RecoveryTokenScheduler recoveryTokenScheduler = new RecoveryTokenScheduler(recoveryTokenSchedulerService);

        Assertions.assertDoesNotThrow(recoveryTokenScheduler::scheduledExpiredRecoveryTokenProcessor);
    }
}
