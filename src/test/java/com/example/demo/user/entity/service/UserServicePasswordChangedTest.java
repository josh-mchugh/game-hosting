package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserPasswordChangedEvent;
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
public class UserServicePasswordChangedTest {

    @Autowired
    private IUserService userService;

    @Test
    public void whenPasswordChangedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handlePasswordChanged(null));
    }

    @Test
    public void whenPasswordChangedHasInvalidIdThenExpectException() {

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .id(UUID.randomUUID())
                .password("password")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handlePasswordChanged(event));
    }

    @Test
    public void whenPasswordChangedHasNullIdThenExceptException() {

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handlePasswordChanged(event));
    }

    @Test
    public void whenPasswordChangedHasPasswordThenReturnPassword() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .id(id)
                .password("new-password")
                .build();

        User user = userService.handlePasswordChanged(event);

        Assertions.assertEquals("new-password", user.getPassword());
    }

    @Test
    public void whenPasswordChangedIsValidThenExpectNullRecoveryToken() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .id(id)
                .password("new-password")
                .build();

        User user = userService.handlePasswordChanged(event);

        Assertions.assertNull(user.getRecoveryToken());
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
