package com.example.demo.web.password.reset.command.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.web.password.reset.command.service.IResetPasswordService;
import com.example.demo.web.password.reset.command.service.model.PasswordResetRequest;
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
public class ResetPasswordServiceResetPasswordTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IResetPasswordService resetPasswordService;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void testHandleResetPassword() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        UserRecoveryTokenCreatedEvent recoveryTokenCreatedEvent = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1000L * 60))
                .build();

        user = userService.handleRecoveryTokenCreated(recoveryTokenCreatedEvent);

        PasswordResetRequest resetRequest = PasswordResetRequest.builder()
                .password("newPassword1!")
                .token(user.getRecoveryToken().getToken())
                .build();

        resetPasswordService.handlePasswordReset(resetRequest);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any());
    }
}
