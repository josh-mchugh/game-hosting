package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserServiceRecoveryTokenCreatedTest {

    @Autowired
    private IUserService userService;

    @Test
    public void whenRecoveryTokenCreatedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleRecoveryTokenCreated(null));
    }

    @Test
    public void whenRecoveryTokenCreatedHasInvalidIdThenExpectException() {

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleRecoveryTokenCreated(event));
    }

    @Test
    public void whenRecoveryTokenCreatedHasNullIdThenExceptException() {

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleRecoveryTokenCreated(event));
    }

    @Test
    public void whenRecoveryTokenCreatedHasNullRecoveryTokenIdThenThrowException() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token("token")
                .build();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .recoveryToken(recoveryToken)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleRecoveryTokenCreated(event));
    }

    @Test
    public void whenRecoveryTokenCreatedHasTokenThenExpectToken() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now())
                .build();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .recoveryToken(recoveryToken)
                .build();

        User user = userService.handleRecoveryTokenCreated(event);

        Assertions.assertEquals("token", user.getRecoveryToken().getToken());
    }

    @Test
    public void whenRecoveryTokenCreatedHasNullTokenThenExpectException() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token(null)
                .build();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .recoveryToken(recoveryToken)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleRecoveryTokenCreated(event));
    }

    @Test
    public void whenRecoveryTokenCreatedHasSentDateThenExpectSentDate() {

        UUID id = UUID.randomUUID();
        LocalDateTime sentDate = LocalDateTime.now();
        createUser(id);

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(sentDate)
                .expirationDate(LocalDateTime.now())
                .build();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .recoveryToken(recoveryToken)
                .build();

        User user = userService.handleRecoveryTokenCreated(event);

        Assertions.assertEquals(sentDate, user.getRecoveryToken().getSentDate());
    }

    @Test
    public void whenRecoveryTokenCreatedHasNullSentDateThenExpectedException() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(null)
                .expirationDate(LocalDateTime.now())
                .build();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .recoveryToken(recoveryToken)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleRecoveryTokenCreated(event));
    }

    @Test
    public void whenRecoveryTokenCreatedHasExpirationDateThenReturnExpirationDate() {

        UUID id = UUID.randomUUID();
        LocalDateTime expirationDate = LocalDateTime.now();
        createUser(id);

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(LocalDateTime.now())
                .expirationDate(expirationDate)
                .build();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .recoveryToken(recoveryToken)
                .build();

        User user = userService.handleRecoveryTokenCreated(event);

        Assertions.assertEquals(expirationDate, user.getRecoveryToken().getExpirationDate());
    }

    @Test
    public void whenRecoveryTokenCreatedHasNullExpirationDateThenExpectException() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(LocalDateTime.now())
                .expirationDate(null)
                .build();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .recoveryToken(recoveryToken)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleRecoveryTokenCreated(event));
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
