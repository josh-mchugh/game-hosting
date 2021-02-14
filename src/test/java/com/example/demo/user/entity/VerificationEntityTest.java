package com.example.demo.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class VerificationEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        VerificationEntity entity = new VerificationEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        VerificationEntity entity = new VerificationEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        VerificationEntity entity = new VerificationEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        VerificationEntity entity = new VerificationEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        VerificationEntity entity = new VerificationEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        VerificationEntity entity = new VerificationEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasUserEntityThenReturnUserEntity() {

        UserEntity userEntity = new UserEntity();

        VerificationEntity entity = new VerificationEntity();
        entity.setUserEntity(userEntity);

        Assertions.assertEquals(userEntity, entity.getUserEntity());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        VerificationEntity entity = new VerificationEntity();
        entity.setStatus(VerificationStatus.UNVERIFIED);

        Assertions.assertEquals(VerificationStatus.UNVERIFIED, entity.getStatus());
    }

    @Test
    public void whenEntityHasTokenThenReturnToken() {

        VerificationEntity entity = new VerificationEntity();
        entity.setToken("token");

        Assertions.assertEquals("token", entity.getToken());
    }

    @Test
    public void whenEntityHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        VerificationEntity entity = new VerificationEntity();
        entity.setSentDate(sentDate);

        Assertions.assertEquals(sentDate, entity.getSentDate());
    }

    @Test
    public void whenEntityHasVerificationDateThenReturnVerificationDate() {

        LocalDateTime verificationDate = LocalDateTime.now();

        VerificationEntity entity = new VerificationEntity();
        entity.setVerificationDate(verificationDate);

        Assertions.assertEquals(verificationDate, entity.getVerificationDate());
    }
}
