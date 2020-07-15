package com.example.demo.web.registration.service;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.util.AppUrlUtil;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService{

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final IEmailService emailService;

    @Override
    public User handleCreateNewUser(RegistrationCreateUserRequest request) {

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .build();

        User user = userService.handleCreateUser(userCreateRequest);

        EmailCreateRequest welcomeEmailRequest = EmailCreateRequest.builder()
                .toAddress(user.getEmail())
                .template(EmailTemplate.WELCOME)
                .build();

        emailService.handleCreateEmail(welcomeEmailRequest);

        EmailCreateRequest verificationEmail = EmailCreateRequest.builder()
                .toAddress(user.getEmail())
                .template(EmailTemplate.USER_VERIFICATION)
                .context("verificationUrl", appUrlUtil.getAppUrl(String.format("/verify/%s", user.getVerification().getToken())))
                .build();

        emailService.handleCreateEmail(verificationEmail);

        return user;
    }
}
