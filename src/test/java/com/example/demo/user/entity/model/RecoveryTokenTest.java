package com.example.demo.user.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecoveryTokenTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RecoveryToken recoveryToken = RecoveryToken.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, recoveryToken.getId());
    }

    @Test
    public void whenModelHasTokenThenReturnToken() {

        RecoveryToken recoveryToken = RecoveryToken.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", recoveryToken.getToken());
    }

    @Test
    public void whenModelHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        RecoveryToken recoveryToken = RecoveryToken.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, recoveryToken.getSentDate());
    }

    @Test
    public void whenModelHasExpirationDateThenReturnExpirationDate() {

        LocalDateTime expirationDate = LocalDateTime.now();

        RecoveryToken recoveryToken = RecoveryToken.builder()
                .expirationDate(expirationDate)
                .build();

        Assertions.assertEquals(expirationDate, recoveryToken.getExpirationDate());
    }

    @Test
    public void whenModelToString() {

        RecoveryToken recoveryToken = recoveryToken();

        String expected = "RecoveryToken(id=0fa590d5-d522-477c-8997-507fa8a3ad3d, token=token, sentDate=2020-11-22T11:56, expirationDate=2020-11-22T11:56)";

        Assertions.assertEquals(expected, recoveryToken.toString());
    }

    @Test
    public void whenModelHashCode() {

        RecoveryToken recoveryToken = recoveryToken();

        Assertions.assertEquals(319617971, recoveryToken.hashCode());
    }

    @Test
    public void whenModelEquals() {

        RecoveryToken recoveryToken1 = recoveryToken();
        RecoveryToken recoveryToken2 = recoveryToken();

        Assertions.assertEquals(recoveryToken1, recoveryToken2);
    }

    @Test
    public void whenModelNotEquals() {

        RecoveryToken recoveryToken = recoveryToken();

        Assertions.assertNotEquals(recoveryToken, RecoveryToken.builder().build());
    }

    private RecoveryToken recoveryToken() {

        return RecoveryToken.builder()
                .id(UUID.fromString("0fa590d5-d522-477c-8997-507fa8a3ad3d"))
                .token("token")
                .sentDate(LocalDateTime.of(2020, 11, 22, 11, 56))
                .expirationDate(LocalDateTime.of(2020, 11, 22, 11, 56))
                .build();
    }
}
