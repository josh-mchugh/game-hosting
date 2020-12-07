package com.example.demo.awx.credential.entity;

import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class AwxCredentialEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasAwxOrganizationEntityThenReturnAwxOrganizationEntity() {

        AwxOrganizationEntity awxOrganizationEntity = new AwxOrganizationEntity();

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);

        Assertions.assertEquals(awxOrganizationEntity, entity.getAwxOrganizationEntity());
    }

    @Test
    public void whenEntityHasCredentialIdThenReturnCredentialId() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setAwxId(1L);

        Assertions.assertEquals(1L, entity.getAwxId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }

    @Test
    public void whenEntityHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setPrivateKey("privateKey");

        Assertions.assertEquals("privateKey", entity.getPrivateKey());
    }

    @Test
    public void whenEntityHasPassPhraseThenReturnPassPhrase() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setPassphrase("passPhrase");

        Assertions.assertEquals("passPhrase", entity.getPassphrase());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setType(AwxCredentialType.MACHINE);

        Assertions.assertEquals(AwxCredentialType.MACHINE, entity.getType());
    }
}
