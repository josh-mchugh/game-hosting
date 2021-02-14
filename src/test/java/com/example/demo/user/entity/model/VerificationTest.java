package com.example.demo.user.entity.model;

import com.example.demo.user.entity.VerificationStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class VerificationTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        Verification verification = Verification.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, verification.getId());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        Verification verification = Verification.builder()
                .status(VerificationStatus.UNVERIFIED)
                .build();

        Assertions.assertEquals(VerificationStatus.UNVERIFIED, verification.getStatus());
    }

    @Test
    public void whenModelHasTokenThenReturnToken() {

        Verification verification = Verification.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", verification.getToken());
    }

    @Test
    public void whenModelHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        Verification verification = Verification.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, verification.getSentDate());
    }

    @Test
    public void whenModelHasVerificationDateThenReturnVerificationDate() {

        LocalDateTime verificationDate = LocalDateTime.now();

        Verification verification = Verification.builder()
                .verificationDate(verificationDate)
                .build();

        Assertions.assertEquals(verificationDate, verification.getVerificationDate());
    }

    @Test
    public void whenModelIsVerifiedThenReturnTrue() {

        Verification verification = Verification.builder()
                .status(VerificationStatus.VERIFIED)
                .build();

        Assertions.assertTrue(verification.isVerified());
    }

    @Test
    public void whenModelIsUnverifiedThenReturnFalse() {

        Verification verification = Verification.builder()
                .status(VerificationStatus.UNVERIFIED)
                .build();

        Assertions.assertFalse(verification.isVerified());
    }

    @Test
    public void whenModelToString() {

        Verification verification = verification();

        String expected = "Verification(id=9aefecad-5852-4e3c-99d9-9dcc283a1e7e, status=UNVERIFIED, token=token, sentDate=2020-11-22T11:49, verificationDate=2020-11-22T11:50)";

        Assertions.assertEquals(expected, verification.toString());
    }

    @Test
    public void whenModelHashCode() {

        Verification verification = Verification.builder()
                .id(UUID.fromString("9aefecad-5852-4e3c-99d9-9dcc283a1e7e"))
                .token("token")
                .sentDate(LocalDateTime.of(2020, 11, 22, 11, 49))
                .verificationDate(LocalDateTime.of(2020, 11, 22, 11, 50))
                .build();

        Assertions.assertEquals(-1610425222, verification.hashCode());
    }

    @Test
    public void whenModelEquals() {

        Verification verification1 = verification();
        Verification verification2 = verification();

        Assertions.assertEquals(verification1, verification2);
    }

    @Test
    public void whenModelNotEquals() {

        Verification verification = verification();

        Assertions.assertNotEquals(verification, Verification.builder().build());
    }

    private Verification verification() {

        return Verification.builder()
                .id(UUID.fromString("9aefecad-5852-4e3c-99d9-9dcc283a1e7e"))
                .status(VerificationStatus.UNVERIFIED)
                .token("token")
                .sentDate(LocalDateTime.of(2020, 11, 22, 11, 49))
                .verificationDate(LocalDateTime.of(2020, 11, 22, 11, 50))
                .build();
    }
}
