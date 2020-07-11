package com.example.demo.recovery.scheduler;

import com.example.demo.recovery.model.RecoveryToken;
import com.example.demo.recovery.service.IRecoveryTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoveryTokenScheduler {

    private final IRecoveryTokenService recoveryTokenService;

    @Scheduled(fixedDelayString = "${app.password.recovery-scheduler-delay}")
    public void scheduledExpiredRecoveryTokenProcessor() {

        while(recoveryTokenService.existsExpiredRecoveryTokens()) {

            recoveryTokenService.getExpiredRecoveryTokens().stream()
                    .map(RecoveryToken::getId)
                    .forEach(recoveryTokenService::handleDeleteRecoveryToken);
        }
    }
}
