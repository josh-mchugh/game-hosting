package com.example.demo.web.registration.command.service;

import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.web.registration.command.service.model.RegistrationCreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationCommandService implements IRegistrationCommandService {

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
