package com.example.demo.user.entity.mapper;

import com.example.demo.user.entity.VerificationEntity;
import com.example.demo.user.entity.VerificationStatus;
import com.example.demo.user.entity.model.Verification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class VerificationMapperTest {

    @Test
    public void whenEntityIsNullReturnNull() {

        Assertions.assertNull(VerificationMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(VerificationMapper.map(new VerificationEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        VerificationEntity entity = new VerificationEntity();
        entity.setId("id");

        Verification verification = VerificationMapper.map(entity);

        Assertions.assertEquals("id", verification.getId());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        VerificationEntity entity = new VerificationEntity();
        entity.setStatus(VerificationStatus.UNVERIFIED);

        Verification verification = VerificationMapper.map(entity);

        Assertions.assertEquals(VerificationStatus.UNVERIFIED, verification.getStatus());
    }

    @Test
    public void whenEntityHasTokenThenReturnToken() {

        VerificationEntity entity = new VerificationEntity();
        entity.setToken("token");

        Verification verification = VerificationMapper.map(entity);

        Assertions.assertEquals("token", verification.getToken());
    }

    @Test
    public void whenEntityHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        VerificationEntity entity = new VerificationEntity();
        entity.setSentDate(sentDate);

        Verification verification = VerificationMapper.map(entity);

        Assertions.assertEquals(sentDate, verification.getSentDate());
    }

    @Test
    public void whenEntityHasVerificationDateThenReturnVerificationDate() {

        LocalDateTime verificationDate = LocalDateTime.now();

        VerificationEntity entity = new VerificationEntity();
        entity.setVerificationDate(verificationDate);

        Verification verification = VerificationMapper.map(entity);

        Assertions.assertEquals(verificationDate, verification.getVerificationDate());
    }
}
