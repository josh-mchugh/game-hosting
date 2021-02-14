package com.example.demo.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecoveryTokenEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasUserEntityThenReturnUserEntity() {

        UserEntity userEntity = new UserEntity();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setUserEntity(userEntity);

        Assertions.assertEquals(userEntity, entity.getUserEntity());
    }

    @Test
    public void whenEntityHasTokenThenReturnToken() {

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setToken("token");

        Assertions.assertEquals("token", entity.getToken());
    }

    @Test
    public void whenEntityHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setSentDate(sentDate);

        Assertions.assertEquals(sentDate, entity.getSentDate());
    }

    @Test
    public void whenEntityHasExpirationDateThenReturnExpirationDate() {

        LocalDateTime expirationDate = LocalDateTime.now();

        RecoveryTokenEntity entity = new RecoveryTokenEntity();
        entity.setExpirationDate(expirationDate);

        Assertions.assertEquals(expirationDate, entity.getExpirationDate());
    }
}
