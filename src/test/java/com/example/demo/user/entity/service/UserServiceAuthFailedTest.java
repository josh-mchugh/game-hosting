package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserAuthFailedEvent;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
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
public class UserServiceAuthFailedTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenAuthFailedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleAuthFailed(null));
    }

    @Test
    public void whenAuthFailedHasInvalidIdThenExpectException() {

        UserAuthFailedEvent event = new UserAuthFailedEvent(UUID.randomUUID());

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleAuthFailed(event));
    }

    @Test
    public void whenAuthFailedHasNullIdThenThrowException() {

        UserAuthFailedEvent event = new UserAuthFailedEvent(null);

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleAuthFailed(event));
    }

    @Test
    public void whenAuthFailedIsValidThenExpectInvalidLoginsAttemptIncremented() {

        UUID id = UUID.randomUUID();

        UserCreatedEvent createdEvent = UserCreatedEvent.builder()
                .id(id)
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        userService.handleCreated(createdEvent);

        UserAuthFailedEvent event = new UserAuthFailedEvent(id);

        User user = userService.handleAuthFailed(event);

        Assertions.assertEquals(1, user.getInvalidLoginAttempts());
    }
}
