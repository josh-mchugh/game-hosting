package com.example.demo.web.registration.command.service;

import com.example.demo.web.registration.command.service.model.RegistrationCreateUserRequest;

public interface IRegistrationCommandService {

    void handleCreateNewUser(RegistrationCreateUserRequest request);
}
