package com.example.demo.web.registration.service;

import com.example.demo.user.model.User;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;

public interface IRegistrationService {

    User handleCreateNewUser(RegistrationCreateUserRequest request);
}
