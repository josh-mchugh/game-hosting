package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserAuthSuccessEvent;
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
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserServiceAuthSuccessTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenAuthSuccessHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleAuthSuccess(null));
    }

    @Test
    public void whenAuthSuccessHasInvalidIdThenExpectException() {

        UserAuthSuccessEvent event = UserAuthSuccessEvent.builder()
                .id(UUID.randomUUID())
                .lastLoginDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleAuthSuccess(event));
    }

    @Test
    public void whenAuthSuccessHasNullIdThenExceptException() {

        UserAuthSuccessEvent event = UserAuthSuccessEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleAuthSuccess(event));
    }

    @Test
    public void whenAuthSuccessIsValidThenExpectInvalidLoginAttemptsZero() {

        UUID id = UUID.randomUUID();

        createUser(id);

        UserAuthSuccessEvent event = UserAuthSuccessEvent.builder()
                .id(id)
                .lastLoginDate(LocalDateTime.now())
                .build();

        User user = userService.handleAuthSuccess(event);

        Assertions.assertEquals(0L, user.getInvalidLoginAttempts());
    }

    @Test
    public void whenAuthSuccessIsValidThenExpectSentDate() {

        UUID id = UUID.randomUUID();
        LocalDateTime sentDate = LocalDateTime.now();

        createUser(id);

        UserAuthSuccessEvent event = UserAuthSuccessEvent.builder()
                .id(id)
                .lastLoginDate(sentDate)
                .build();

        User user = userService.handleAuthSuccess(event);

        Assertions.assertEquals(sentDate, user.getLastLoginDate());
    }

    private void createUser(UUID id) {

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
