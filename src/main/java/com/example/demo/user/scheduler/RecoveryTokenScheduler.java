package com.example.demo.user.scheduler;

import com.example.demo.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecoveryTokenScheduler {

    private final IUserService userService;

    @Scheduled(fixedDelayString = "${app.password.recovery-scheduler-delay}")
    public void scheduledExpiredRecoveryTokenProcessor() {

        while(userService.existsByRecoveryTokensExpired()) {

            userService.getByRecoveryTokensExpired().stream()
                    .map(user -> user.getRecoveryToken().getId())
                    .forEach(userService::handleDeleteRecoveryTokenById);
        }
    }
}
