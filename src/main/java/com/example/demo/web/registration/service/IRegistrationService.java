package com.example.demo.web.registration.service;

import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;

public interface IRegistrationService {

    void handleCreateNewUser(RegistrationCreateUserRequest request);
}
