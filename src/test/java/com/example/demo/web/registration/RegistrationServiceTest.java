package com.example.demo.web.registration;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.web.registration.service.IRegistrationService;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordResponse;
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

    @Autowired
    private IUserService userService;

    @Test
    public void testNotExistUserEmail() {

        boolean exists = registrationService.existsEmailAddress("kdlfkgjsdlfjg@fjsldfjaeiawe.com");

        Assertions.assertFalse(exists);
    }

    @Test
    public void testExistUserEmail() {

        UserCreateRequest request = UserCreateRequest.builder()
                .email("registration-exists@test.com")
                .password("Password1!")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .build();

        userService.handleCreateUser(request);

        boolean exists = registrationService.existsEmailAddress(request.getEmail());

        Assertions.assertTrue(exists);
    }

    @Test
    public void testInvalidValidatePasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("password", "password");
        ValidatePasswordResponse response = registrationService.handleValidatePassword(request);

        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("Password is invalid", response.getErrorMessage());
    }

    @Test
    public void testMismatchPasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("password1", "password2");
        ValidatePasswordResponse response = registrationService.handleValidatePassword(request);

        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("Passwords must match", response.getErrorMessage());
    }

    @Test
    public void testEmptyPasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("", "");
        ValidatePasswordResponse response = registrationService.handleValidatePassword(request);

        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("Password is invalid", response.getErrorMessage());
    }

    @Test
    public void testValidPasswords() {

        ValidatePasswordRequest request = new ValidatePasswordRequest("Password1!", "Password1!");
        ValidatePasswordResponse response = registrationService.handleValidatePassword(request);

        Assertions.assertTrue(response.isValid());
    }

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
    }
}
