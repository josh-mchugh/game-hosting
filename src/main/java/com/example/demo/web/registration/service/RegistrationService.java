package com.example.demo.web.registration.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.util.AppUrlUtil;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService{

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final CommandGateway commandGateway;

    @Override
    public User handleCreateNewUser(RegistrationCreateUserRequest request) {

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .build();

        User user = userService.handleCreateUser(userCreateRequest);

        EmailCreateCommand welcomeEmailCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(user.getEmail())
                .template(EmailTemplate.WELCOME)
                .build();

        commandGateway.send(welcomeEmailCommand);

        EmailCreateCommand verificationEmailCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(user.getEmail())
                .template(EmailTemplate.USER_VERIFICATION)
                .bodyContext("verificationUrl", appUrlUtil.getAppUrl(String.format("/verify/%s", user.getVerification().getToken())))
                .build();

        commandGateway.send(verificationEmailCommand);

        return user;
    }
}
