package com.example.demo.web.registration.service;

import com.example.demo.user.model.User;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordResponse;

public interface IRegistrationService {

    boolean existsEmailAddress(String email);

    ValidatePasswordResponse handleValidatePassword(ValidatePasswordRequest request);

    User handleCreateNewUser(RegistrationCreateUserRequest request);
}
