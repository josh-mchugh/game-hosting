package com.example.demo.user.entity.mapper;

import com.example.demo.user.entity.RecoveryTokenEntity;
import com.example.demo.user.entity.model.RecoveryToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecoveryTokenMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(RecoveryTokenMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(RecoveryTokenMapper.map(new RecoveryTokenEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setId(id);

        RecoveryToken recoveryToken = RecoveryTokenMapper.map(entity);

        Assertions.assertEquals(id, recoveryToken.getId());
    }

    @Test
    public void whenEntityHasTokenThenReturnToken() {

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setToken("token");

        RecoveryToken recoveryToken = RecoveryTokenMapper.map(entity);

        Assertions.assertEquals("token", recoveryToken.getToken());
    }

    @Test
    public void whenEntityHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setSentDate(sentDate);

        RecoveryToken recoveryToken = RecoveryTokenMapper.map(entity);

        Assertions.assertEquals(sentDate, recoveryToken.getSentDate());
    }

    @Test
    public void whenEntityHasExpirationDateThenReturnExpirationDate() {

        LocalDateTime expirationDate = LocalDateTime.now();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setExpirationDate(expirationDate);

        RecoveryToken recoveryToken = RecoveryTokenMapper.map(entity);

        Assertions.assertEquals(expirationDate, recoveryToken.getExpirationDate());
    }
}
