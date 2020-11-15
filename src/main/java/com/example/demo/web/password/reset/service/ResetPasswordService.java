package com.example.demo.web.password.reset.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserPasswordResetRequest;
import com.example.demo.util.AppUrlUtil;
import com.example.demo.web.password.reset.service.model.PasswordResetRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResetPasswordService implements IResetPasswordService {

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final CommandGateway commandGateway;

    @Override
    public User handlePasswordReset(PasswordResetRequest request) {

        UserPasswordResetRequest resetRequest = UserPasswordResetRequest.builder()
                .password(request.getPassword())
                .token(request.getToken())
                .build();

         User user = userService.handlePasswordReset(resetRequest);

        EmailCreateCommand emailCreateCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(user.getEmail())
                .template(EmailTemplate.PASSWORD_RESET)
                .bodyContext("email", user.getEmail())
                .bodyContext("loginUrl", appUrlUtil.getAppUrl("/login"))
                .bodyContext("forgotPasswordUrl", appUrlUtil.getAppUrl("/forgot-password", new ImmutablePair<>("email", user.getEmail())))
                .build();

        commandGateway.send(emailCreateCommand);

        return user;
    }
}
