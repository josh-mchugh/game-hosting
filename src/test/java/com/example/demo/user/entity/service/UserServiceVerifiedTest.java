package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
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
public class UserServiceVerifiedTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenVerifiedHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleVerified(null));
    }

    @Test
    public void whenVerifiedHasInvalidIdThenExpectException() {

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(UUID.randomUUID())
                .verifiedDate(LocalDateTime.now())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleVerified(event));
    }

    @Test
    public void whenVerifiedHasNullIdThenExceptException() {

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> userService.handleVerified(event));
    }

    @Test
    public void whenVerifiedIsValidThenExpectStatusVerified() {

        UUID id = UUID.randomUUID();

        createUser(id);

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(id)
                .build();

        User user = userService.handleVerified(event);

        Assertions.assertEquals(VerificationStatus.VERIFIED, user.getVerification().getStatus());
    }

    @Test
    public void whenVerificationIsValidThenExpectVerificationDate() {

        UUID id = UUID.randomUUID();
        LocalDateTime verificationDate = LocalDateTime.now();

        createUser(id);

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(id)
                .verifiedDate(verificationDate)
                .build();

        User user = userService.handleVerified(event);

        Assertions.assertEquals(verificationDate, user.getVerification().getVerificationDate());
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
