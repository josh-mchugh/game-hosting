package com.example.demo.web.password.forgot.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.util.AppUrlUtil;
import com.example.demo.web.password.forgot.service.model.ForgotPasswordResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ForgotPasswordService implements IForgotPasswordService {

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final CommandGateway commandGateway;

    @Override
    public ForgotPasswordResponse handleForgotPassword(String emailAddress) {

        if(userService.existsUserByEmail(emailAddress)) {

            User user = userService.handleCreateRecoveryToken(emailAddress);

            EmailCreateCommand emailCreateCommand = EmailCreateCommand.builder()
                    .id(UUID.randomUUID())
                    .toAddress(emailAddress)
                    .template(EmailTemplate.PASSWORD_RECOVERY)
                    .bodyContext("email", emailAddress)
                    .bodyContext("resetPasswordUrl", appUrlUtil.getAppUrl(String.format("%s/%s", "/reset-password/", user.getRecoveryToken().getToken())))
                    .build();

            commandGateway.send(emailCreateCommand);

            return ForgotPasswordResponse.builder()
                    .success(true)
                    .emailAddress(emailAddress)
                    .build();
        }

        return ForgotPasswordResponse.builder()
                .success(false)
                .emailAddress(emailAddress)
                .build();
    }
}
