package com.example.demo.web.verify.service;

import com.example.demo.user.aggregate.command.UserVerifyResetCommand;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.web.verification.service.IVerifyService;
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
public class VerifyServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IVerifyService verifyService;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void testHandleResendVerificationEmail() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        verifyService.handleResendVerificationEmail(user.getId());

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserVerifyResetCommand.class));
    }
}
