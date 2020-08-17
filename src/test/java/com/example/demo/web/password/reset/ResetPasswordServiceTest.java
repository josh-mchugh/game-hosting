package com.example.demo.web.password.reset;

import com.example.demo.sample.TestUserUtil;
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
    private IResetPasswordService resetPasswordService;

    @Test
    public void testHandleResetPassword() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user1@reset-password-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        user = userService.handleCreateRecoveryToken(user.getEmail());

        PasswordResetRequest resetRequest = PasswordResetRequest.builder()
                .password("newPassword1!")
                .token(user.getRecoveryToken().getToken())
                .build();

        User updatedUser = resetPasswordService.handlePasswordReset(resetRequest);

        Assertions.assertNotEquals(user.getPassword(), updatedUser.getPassword());
        Assertions.assertNotEquals(user.getRecoveryToken(), updatedUser.getRecoveryToken());
    }
}
