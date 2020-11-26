package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class UserRecoveryTokenCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasRecoveryTokenThenReturnRecoveryToken() {

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1L))
                .build();

        Assertions.assertNotNull(event.getRecoveryToken());
    }

    @Test
    public void whenRecoveryTokenHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, recoveryToken.getId());
    }

    @Test
    public void whenRecoveryTokenHasTokenThenReturnToken() {

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", recoveryToken.getToken());
    }

    @Test
    public void whenRecoverTokenHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, recoveryToken.getSentDate());
    }

    @Test
    public void whenRecoverTokenHasExpirationDateThenReturnExpirationDate() {

        LocalDateTime expirationDate = LocalDateTime.now();

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .expirationDate(expirationDate)
                .build();

        Assertions.assertEquals(expirationDate, recoveryToken.getExpirationDate());
    }

    @Test
    public void whenCreateRecoveryTokenOffsetThenExpectOffset() {

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.createRecoveryToken(100L);

        Assertions.assertEquals(recoveryToken.getExpirationDate(), recoveryToken.getSentDate().plus(100L, ChronoUnit.MILLIS));
    }

    @Test
    public void whenRecoveryTokenToString() {

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = recoveryToken();

        String expected = "UserRecoveryTokenCreatedEvent.RecoveryToken(id=aea57a0a-2305-4ef8-9b46-044daf9ff054, token=token, sentDate=2020-11-21T15:35, expirationDate=2020-11-21T15:35)";

        Assertions.assertEquals(expected, recoveryToken.toString());
    }

    @Test
    public void whenRecoveryTokenHashCode() {

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = recoveryToken();

        Assertions.assertEquals(-1977225085, recoveryToken.hashCode());
    }

    @Test
    public void whenRecoveryTokenEquals() {

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken1 = recoveryToken();
        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken2 = recoveryToken();

        Assertions.assertEquals(recoveryToken1, recoveryToken2);
    }

    @Test
    public void whenRecoveryTokenNotEquals() {

        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = recoveryToken();

        Assertions.assertNotEquals(recoveryToken, UserRecoveryTokenCreatedEvent.RecoveryToken.builder().build());
    }

    @Test
    public void whenEventToString() {

        UserRecoveryTokenCreatedEvent event = event();

        String expected = "UserRecoveryTokenCreatedEvent(id=33d5ee9c-0ed8-44f8-a0a0-12160a112685, recoveryToken=UserRecoveryTokenCreatedEvent.RecoveryToken(id=aea57a0a-2305-4ef8-9b46-044daf9ff054, token=token, sentDate=2020-11-21T15:35, expirationDate=2020-11-21T15:35))";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserRecoveryTokenCreatedEvent event = event();

        Assertions.assertEquals(-2103533303, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserRecoveryTokenCreatedEvent event1 = event();
        UserRecoveryTokenCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserRecoveryTokenCreatedEvent event = event();

        Assertions.assertNotEquals(event, UserRecoveryTokenCreatedEvent.builder().build());
    }

    private UserRecoveryTokenCreatedEvent event() {

        return UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString("33d5ee9c-0ed8-44f8-a0a0-12160a112685"))
                .recoveryToken(recoveryToken())
                .build();
    }

    private UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken() {

        return UserRecoveryTokenCreatedEvent.RecoveryToken.builder()
                .id(UUID.fromString("aea57a0a-2305-4ef8-9b46-044daf9ff054"))
                .token("token")
                .sentDate(LocalDateTime.of(2020, 11, 21, 15, 35))
                .expirationDate(LocalDateTime.of(2020, 11, 21, 15, 35))
                .build();
    }
}
