package com.example.demo.user.scheduler;

import com.example.demo.user.scheduler.service.IRecoveryTokenSchedulerService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

public class RecoveryTokenSchedulerTest {

    @Test
    public void whenSchedulerProcessesUsers() {

        IRecoveryTokenSchedulerService recoveryTokenSchedulerService = Mockito.mock(IRecoveryTokenSchedulerService.class);
        Mockito.when(recoveryTokenSchedulerService.processExpiredRecoveryTokens()).thenReturn(ImmutableList.of(UUID.randomUUID()));

        RecoveryTokenScheduler recoveryTokenScheduler = new RecoveryTokenScheduler(recoveryTokenSchedulerService);

        Assertions.assertDoesNotThrow(recoveryTokenScheduler::scheduledExpiredRecoveryTokenProcessor);
    }
    
    @Test
    public void whenSchedulerProcessesNoUsers() {

        IRecoveryTokenSchedulerService recoveryTokenSchedulerService = Mockito.mock(IRecoveryTokenSchedulerService.class);
        Mockito.when(recoveryTokenSchedulerService.processExpiredRecoveryTokens()).thenReturn(ImmutableList.of());

        RecoveryTokenScheduler recoveryTokenScheduler = new RecoveryTokenScheduler(recoveryTokenSchedulerService);

        Assertions.assertDoesNotThrow(recoveryTokenScheduler::scheduledExpiredRecoveryTokenProcessor);
    }
}
