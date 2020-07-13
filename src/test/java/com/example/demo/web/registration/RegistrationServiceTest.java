package com.example.demo.web.registration;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.entity.VerificationStatus;
import com.example.demo.web.registration.service.IRegistrationService;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class RegistrationServiceTest {

    @Autowired
    private IRegistrationService registrationService;

    @Test
    public void testRegistrationCreateUser() {

        RegistrationCreateUserRequest request = RegistrationCreateUserRequest.builder()
                .email("registration-user@test.com")
                .password("Password1!")
                .build();

        User user = registrationService.handleCreateNewUser(request);

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(request.getEmail(), user.getEmail());
        Assertions.assertNotNull(user.getPassword());
        Assertions.assertEquals(UserType.REGULAR, user.getType());
        Assertions.assertEquals(UserState.ACTIVE, user.getState());

        Assertions.assertNotNull(user.getVerification().getId());
        Assertions.assertNotNull(user.getVerification().getToken());
        Assertions.assertEquals(VerificationStatus.UNVERIFIED, user.getVerification().getStatus());
        Assertions.assertNotNull(user.getVerification().getSendDate());
        Assertions.assertNull(user.getVerification().getVerificationDate());
    }
}
