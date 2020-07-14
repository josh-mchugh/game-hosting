package com.example.demo.web.password.forgot;

import com.example.demo.test.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.web.password.forgot.service.IForgotPasswordService;
import com.example.demo.web.password.forgot.service.model.ForgotPasswordResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest()
public class ForgotPasswordServiceTest {

    @Autowired
    private IForgotPasswordService forgotPasswordService;

    @Autowired
    private IUserService userService;

    @Test
    public void testForgotPasswordExistingUser() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user1@forgot-password-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        ForgotPasswordResponse response = forgotPasswordService.handleForgotPassword(user.getEmail());

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(user.getEmail(), response.getEmailAddress());
    }

    @Test
    public void testForgotPasswordNonExistingUser() {

        ForgotPasswordResponse response = forgotPasswordService.handleForgotPassword("non-existing@forgot-password-service.com");

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(response.getEmailAddress(),"non-existing@forgot-password-service.com");
    }
}
