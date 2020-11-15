package com.example.demo.web.verification.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.util.AppUrlUtil;
import com.example.demo.web.verification.service.model.VerificationResendResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VerifyService implements IVerifyService {

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final CommandGateway commandGateway;

    @Override
    public VerificationResendResponse handleResendVerificationEmail(String userId) {

        User user = userService.handleResetEmailVerification(userId);

        EmailCreateCommand emailCreateCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(user.getEmail())
                .template(EmailTemplate.USER_VERIFICATION)
                .bodyContext("verificationUrl", appUrlUtil.getAppUrl(String.format("/verify/%s", user.getVerification().getToken())))
                .build();

        commandGateway.send(emailCreateCommand);

        return VerificationResendResponse.builder()
                .success(true)
                .user(user)
                .build();
    }
}
