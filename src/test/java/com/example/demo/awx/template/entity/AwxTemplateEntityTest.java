package com.example.demo.awx.template.entity;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class AwxTemplateEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasAwxCredentialEntityThenReturnAwxCredentialEntity() {

        AwxCredentialEntity awxCredentialEntity = new AwxCredentialEntity();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setAwxCredentialEntity(awxCredentialEntity);

        Assertions.assertEquals(awxCredentialEntity, entity.getAwxCredentialEntity());
    }

    @Test
    public void whenEntityHasAwxInventoryEntityThenReturnAwxInventoryEntity() {

        AwxInventoryEntity awxInventoryEntity = new AwxInventoryEntity();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setAwxInventoryEntity(awxInventoryEntity);

        Assertions.assertEquals(awxInventoryEntity, entity.getAwxInventoryEntity());
    }

    @Test
    public void whenEntityHasAwxPlaybookEntityThenReturnAwxPlaybookEntity() {

        AwxPlayBookEntity awxPlayBookEntity = new AwxPlayBookEntity();

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setAwxPlayBookEntity(awxPlayBookEntity);

        Assertions.assertEquals(awxPlayBookEntity, entity.getAwxPlayBookEntity());
    }

    @Test
    public void whenEntityHasAwxIdThenReturnAwxId() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setAwxId(1L);

        Assertions.assertEquals(1L, entity.getAwxId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setType(TemplateJobType.RUN);

        Assertions.assertEquals(TemplateJobType.RUN, entity.getType());
    }

    @Test
    public void whenEntityHasVerbosityThenReturnVerbosity() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setVerbosity(TemplateVerbosity.NORMAL);

        Assertions.assertEquals(TemplateVerbosity.NORMAL, entity.getVerbosity());
    }
}
