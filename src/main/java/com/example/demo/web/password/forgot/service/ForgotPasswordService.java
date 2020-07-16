package com.example.demo.web.password.forgot.service;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.util.AppUrlUtil;
import com.example.demo.web.password.forgot.service.model.ForgotPasswordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForgotPasswordService implements IForgotPasswordService {

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final IEmailService emailService;

    @Override
    public ForgotPasswordResponse handleForgotPassword(String emailAddress) {

        if(userService.existsUserByEmail(emailAddress)) {

            User user = userService.handleCreateRecoveryToken(emailAddress);

            EmailCreateRequest emailCreateRequest = EmailCreateRequest.builder()
                    .toAddress(emailAddress)
                    .template(EmailTemplate.PASSWORD_RECOVERY)
                    .bodyContext("email", emailAddress)
                    .bodyContext("resetPasswordUrl", appUrlUtil.getAppUrl(String.format("%s/%s", "/reset-password/", user.getRecoveryToken().getToken())))
                    .build();

            emailService.handleCreateEmail(emailCreateRequest);

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
