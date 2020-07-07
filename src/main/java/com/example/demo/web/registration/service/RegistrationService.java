package com.example.demo.web.registration.service;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService{

    private final IUserService userService;
    private final IEmailService emailService;

    @Override
    public boolean existsEmailAddress(String email) {

        return userService.existUserByEmail(email);
    }

    @Override
    public ValidatePasswordResponse handleValidatePassword(ValidatePasswordRequest request) {

        if(StringUtils.isBlank(request.getPassword()) && StringUtils.isBlank(request.getConfirmPassword())) {

            return ValidatePasswordResponse.builder()
                    .valid(false)
                    .errorMessage("Password is invalid")
                    .build();
        }

        if (!StringUtils.equals(request.getPassword(), request.getConfirmPassword())) {

            return ValidatePasswordResponse.builder()
                    .valid(false)
                    .errorMessage("Passwords must match")
                    .build();
        }

        if (!isValidPassword(request.getPassword()) || !isValidPassword(request.getConfirmPassword())) {

            return ValidatePasswordResponse.builder()
                    .valid(false)
                    .errorMessage("Password is invalid")
                    .build();
        }

        return ValidatePasswordResponse.builder()
                .valid(true)
                .build();
    }

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
                .template(EmailTemplate.EMAIL_VERIFICATION)
                .build();

        emailService.handleCreateEmail(verificationEmail);

        return user;
    }

    private boolean isValidPassword(String password) {

        return new PasswordValidator(Arrays.asList(
                    new LengthRule(8, Integer.MAX_VALUE),
                    new CharacterRule(EnglishCharacterData.Digit),
                    new CharacterRule(EnglishCharacterData.Special),
                    new CharacterRule(EnglishCharacterData.UpperCase)
            )).validate(new PasswordData(password))
            .isValid();
    }
}
