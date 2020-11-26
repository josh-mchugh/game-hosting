package com.example.demo.web.password.forgot.service;

import com.example.demo.user.aggregate.command.UserRecoveryTokenCreateCommandTest;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ForgotPasswordServiceTest {

    @Autowired
    private IForgotPasswordService forgotPasswordService;

    @Autowired
    private IUserService userService;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void testForgotPasswordExistingUser() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        forgotPasswordService.handleForgotPassword(user.getEmail());

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any());
    }

    @Test
    public void testForgotPasswordNonExistingUser() {

        forgotPasswordService.handleForgotPassword("non-existing@forgot-password-service.com");

        Mockito.verify(commandGateway, Mockito.times(0)).send(Mockito.any(UserRecoveryTokenCreateCommandTest.class));
    }
}
