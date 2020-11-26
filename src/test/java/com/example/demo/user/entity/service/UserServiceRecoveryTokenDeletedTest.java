package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenDeletedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserServiceRecoveryTokenDeletedTest {

    @Autowired
    private IUserService userService;

    @Test
    public void whenRecoveryTokenDeletedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleRecoveryTokenDelete(null));
    }

    @Test
    public void whenRecoveryTokenDeletedHasInvalidIdThenExpectException() {

        UserRecoveryTokenDeletedEvent event = new UserRecoveryTokenDeletedEvent(UUID.randomUUID());

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleRecoveryTokenDelete(event));
    }

    @Test
    public void whenRecoveryTokenDeletedHasNullIdThenExceptException() {

        UserRecoveryTokenDeletedEvent event = new UserRecoveryTokenDeletedEvent(null);

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleRecoveryTokenDelete(event));
    }

    @Test
    public void whenRecoveryTokenDeletedIsValidThenExpectRecoveryTokenNull() {

        UUID id = UUID.randomUUID();
        createdUser(id);

        UserRecoveryTokenDeletedEvent event = new UserRecoveryTokenDeletedEvent(id);

        User user = userService.handleRecoveryTokenDelete(event);

        Assertions.assertNull(user.getRecoveryToken());
    }

    private void createdUser(UUID id) {

        UserCreatedEvent createdEvent = UserCreatedEvent.builder()
                .id(id)
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        userService.handleCreated(createdEvent);
    }
}
