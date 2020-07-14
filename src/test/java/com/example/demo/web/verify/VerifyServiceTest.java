package com.example.demo.web.verify;

import com.example.demo.test.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.web.verification.service.IVerifyService;
import com.example.demo.web.verification.service.model.VerificationResendResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class VerifyServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IVerifyService verifyService;

    @Test
    public void testHandleResendVerificationEmail() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser( "resend-verification@verify-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        VerificationResendResponse response = verifyService.handleResendVerificationEmail(user.getId());

        Assertions.assertTrue(response.getSuccess());
        Assertions.assertEquals(user.getId(), response.getUser().getId());
        Assertions.assertNotEquals(user.getVerification().getToken(), response.getUser().getVerification().getToken());
    }
}
