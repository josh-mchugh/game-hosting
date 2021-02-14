package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.VerificationStatus;
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
public class UserServiceCreatedTest {

    @Autowired
    private IUserService userService;

    @Test
    public void whenCreatedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleCreated(null));
    }

    @Test
    public void whenCreatedHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(id)
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals(id, user.getId());
    }

    @Test
    public void whenCreatedHasNullIdThrowException() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(null)
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasEmailThenReturnEmail() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals("test@test", user.getEmail());
    }

    @Test
    public void whenCreatedHasNullEmailThenThrowException() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email(null)
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasPasswordThenReturnPassword() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    public void whenCreatedHasNullPasswordThenThrowException() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password(null)
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasStateThenReturnState() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals(UserState.ACTIVE, user.getState());
    }

    @Test
    public void whenCreatedHasNullStateThenThrowException() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(null)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasTypeThenReturnType() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals(UserType.REGULAR, user.getType());
    }

    @Test
    public void whenCreatedHasNullTypeThenReturnType() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(null)
                .verification(UserCreatedEvent.createVerification())
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasInvalidLoginAttemptsThenReturnInvalidLoginAttempts() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals(0L, user.getInvalidLoginAttempts());
    }

    @Test
    public void whenCreatedThenExpectLastLoginDateNull() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertNull(user.getLastLoginDate());
    }

    @Test
    public void whenCreatedVerificationHasIdThenReturnVerificationId() {

        UUID id = UUID.randomUUID();

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(id)
                .token("token")
                .sentDate(LocalDateTime.now())
                .build();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification)
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals(id, user.getVerification().getId());
    }

    @Test
    public void whenCreatedVerificationHasNullIdThenThrowException() {

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(null)
                .token("token")
                .sentDate(LocalDateTime.now())
                .build();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleCreated(event));
    }

    @Test
    public void whenCreatedVerificationHasStatusThenReturnVerificationStatus() {

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(LocalDateTime.now())
                .build();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification)
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals(VerificationStatus.UNVERIFIED, user.getVerification().getStatus());
    }

    @Test
    public void whenCreatedVerificationHasTokenThenReturnToken() {

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(LocalDateTime.now())
                .build();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification)
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals("token", user.getVerification().getToken());
    }

    @Test
    public void whenCreatedVerificationHasNullTokenThenThrowException() {

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(UUID.randomUUID())
                .token(null)
                .sentDate(LocalDateTime.now())
                .build();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleCreated(event));
    }

    @Test
    public void whenCreatedVerificationHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(sentDate)
                .build();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification)
                .build();

        User user = userService.handleCreated(event);

        Assertions.assertEquals(sentDate, user.getVerification().getSentDate());
    }

    @Test
    public void whenCreatedVerificationHasNullSentDateThenThrowException() {

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(null)
                .build();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> userService.handleCreated(event));
    }
}
