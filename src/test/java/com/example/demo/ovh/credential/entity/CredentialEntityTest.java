package com.example.demo.ovh.credential.entity;

import com.example.demo.ovh.credential.entity.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CredentialEntityTest {

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        CredentialEntity entity = new CredentialEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasIDThenReturnId() {

        CredentialEntity entity = new CredentialEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        CredentialEntity entity = new CredentialEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        CredentialEntity entity = new CredentialEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        CredentialEntity entity = new CredentialEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedByDateThenReturnLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        CredentialEntity entity = new CredentialEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        CredentialEntity entity = new CredentialEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasSshKeyIdThenReturnSshKeyId() {

        CredentialEntity entity = new CredentialEntity();
        entity.setSshKeyId("ssh-key-id");

        Assertions.assertEquals("ssh-key-id", entity.getSshKeyId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        CredentialEntity entity = new CredentialEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasPublicKeyThenReturnPublicKey() {

        CredentialEntity entity = new CredentialEntity();
        entity.setPublicKey("public-key");

        Assertions.assertEquals("public-key", entity.getPublicKey());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        CredentialEntity entity = new CredentialEntity();
        entity.setType(CredentialType.ANSIBLE);

        Assertions.assertEquals(CredentialType.ANSIBLE, entity.getType());
    }
}
