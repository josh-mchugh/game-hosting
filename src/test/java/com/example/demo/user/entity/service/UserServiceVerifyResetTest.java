package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.aggregate.event.UserVerifyResetEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.VerificationStatus;
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
public class UserServiceVerifyResetTest {

    @Autowired
    private IUserService userService;

    @Test
    public void whenVerifyResetHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleVerifyReset(null));
    }

    @Test
    public void whenVerifyResetHasInvalidIdThenExpectException() {

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .id(UUID.randomUUID())
                .token("token")
                .sentDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleVerifyReset(event));
    }

    @Test
    public void whenVerifyResetHasNullIdThenExceptException() {

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleVerified(event));
    }

    @Test
    public void whenVerifyResetIsValidThenExpectStatusUnverified() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .id(id)
                .token("token")
                .sentDate(null)
                .build();

        User user = userService.handleVerifyReset(event);

        Assertions.assertEquals(VerificationStatus.UNVERIFIED, user.getVerification().getStatus());
    }

    @Test
    public void whenVerifyResetHasTokenThenExpectToken() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .id(id)
                .token("token")
                .build();

        User user = userService.handleVerifyReset(event);

        Assertions.assertEquals("token", user.getVerification().getToken());
    }

    @Test
    public void whenVerifyResetHasSentDateThenExpectSentDate() {

        UUID id = UUID.randomUUID();
        LocalDateTime sentDate = LocalDateTime.now();
        createUser(id);

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .id(id)
                .token("token")
                .sentDate(sentDate)
                .build();

        User user = userService.handleVerifyReset(event);

        Assertions.assertEquals(sentDate, user.getVerification().getSentDate());
    }

    @Test
    public void whenVerifyResetIsValidThenExpectNullVerificationDate() {

        UUID id = UUID.randomUUID();
        createUser(id);

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .id(id)
                .token("token")
                .sentDate(LocalDateTime.now())
                .build();

        User user = userService.handleVerifyReset(event);

        Assertions.assertNull(user.getVerification().getVerificationDate());
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
