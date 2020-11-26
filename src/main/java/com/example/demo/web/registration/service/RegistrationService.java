package com.example.demo.web.registration.service;

import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService{

    private final CommandGateway commandGateway;

    @Override
    public void handleCreateNewUser(RegistrationCreateUserRequest request) {

        UserCreateRegularCommand command = UserCreateRegularCommand.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        commandGateway.send(command);
    }
}
