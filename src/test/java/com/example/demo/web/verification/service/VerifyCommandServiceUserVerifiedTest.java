package com.example.demo.web.verification.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.command.UserVerifyCommand;
import com.example.demo.user.entity.model.User;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class VerifyCommandServiceUserVerifiedTest {

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IVerifyCommandService verifyService;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenUserIsVerifiedAndExpectCommandCalled() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        verifyService.handleUserVerified(user.getVerification().getToken());

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserVerifyCommand.class));
    }
}
