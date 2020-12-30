package com.example.demo.web.registration.command.service;

import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.web.registration.command.service.model.RegistrationCreateUserRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class RegistrationCommandServiceCreateNewUserTest {

    @Autowired
    private IRegistrationCommandService registrationService;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void testRegistrationCreateUser() {

        RegistrationCreateUserRequest request = RegistrationCreateUserRequest.builder()
                .email("registration-user@test.com")
                .password("Password1!")
                .build();

        registrationService.handleCreateNewUser(request);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserCreateRegularCommand.class));
    }
}
