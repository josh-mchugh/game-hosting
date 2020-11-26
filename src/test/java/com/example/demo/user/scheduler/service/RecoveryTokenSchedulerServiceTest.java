package com.example.demo.user.scheduler.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import com.google.common.collect.ImmutableList;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles({"test"})
public class RecoveryTokenSchedulerServiceTest {

    @Autowired
    private IRecoveryTokenSchedulerService recoveryTokenSchedulerService;

    @Autowired
    private IUserService userService;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenExpiredRecoveryTokenDoNotExistThenReturnEmptyArray() {

        ImmutableList<Object> processedUsers = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        Assertions.assertEquals(0, processedUsers.size());
    }

    @Test
    public void whenExpiredRecoveryTokenExistsThenReturnArray(){

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
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1L))
                .build();

        userService.handleRecoveryTokenCreated(recoveryTokenCreatedEvent);

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ImmutableList<Object> processedUser = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        Assertions.assertEquals(1, processedUser.size());
    }
}
