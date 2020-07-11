package com.example.demo.web.password.reset;

import com.example.demo.recovery.model.RecoveryToken;
import com.example.demo.recovery.service.IRecoveryTokenService;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.web.password.reset.service.IResetPasswordService;
import com.example.demo.web.password.reset.service.model.PasswordResetRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ResetPasswordServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecoveryTokenService recoveryTokenService;

    @Autowired
    private IResetPasswordService resetPasswordService;

    @Test
    public void testHandleResetPassword() {

        UserCreateRequest userCreateRequest = buildUserCreateRequest("user1@reset-password-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        RecoveryToken recoveryToken = recoveryTokenService.handleCreateRecoveryToken(user.getEmail());

        PasswordResetRequest resetRequest = PasswordResetRequest.builder()
                .password("newPassword1!")
                .recoveryTokenId(recoveryToken.getId())
                .build();

        User updatedUser = resetPasswordService.handlePasswordReset(resetRequest);

        Assertions.assertNotEquals(user.getPassword(), updatedUser.getPassword());
        Assertions.assertNotEquals(user.getRecoveryToken(), updatedUser.getRecoveryToken());
    }

    private UserCreateRequest buildUserCreateRequest(String email) {

        return UserCreateRequest.builder()
                .email(email)
                .password("Password1")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .build();
    }
}
