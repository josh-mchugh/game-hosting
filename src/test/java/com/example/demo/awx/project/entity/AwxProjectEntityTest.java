package com.example.demo.awx.project.entity;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.project.entity.ProjectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class AwxProjectEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        ProjectEntity entity = new ProjectEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectEntity entity = new ProjectEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        ProjectEntity entity = new ProjectEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ProjectEntity entity = new ProjectEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        ProjectEntity entity = new ProjectEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        ProjectEntity entity = new ProjectEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        ProjectEntity entity = new ProjectEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasAwxOrganizationEntityThenReturnAwxOrganizationEntity() {

        AwxOrganizationEntity awxOrganizationEntity = new AwxOrganizationEntity();

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setAwxOrganizationEntity(awxOrganizationEntity);

        Assertions.assertEquals(awxOrganizationEntity, entity.getAwxOrganizationEntity());
    }

    @Test
    public void whenEntityHasAwxCredentialEntityThenReturnAwxCredentialEntity() {

        AwxCredentialEntity awxCredentialEntity = new AwxCredentialEntity();

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setAwxCredentialEntity(awxCredentialEntity);

        Assertions.assertEquals(awxCredentialEntity, entity.getAwxCredentialEntity());
    }

    @Test
    public void whenEntityHasAwxIdThenReturnAwxId() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setAwxId(1L);

        Assertions.assertEquals(1L, entity.getAwxId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setDescription("description");

        Assertions.assertEquals("description", entity.getDescription());
    }

    @Test
    public void whenEntityHasScmTypeThenReturnScmType() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setScmType("scmType");

        Assertions.assertEquals("scmType", entity.getScmType());
    }

    @Test
    public void whenEntityHasScmUrlThenReturnScmUrl() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setScmUrl("scmUrl");

        Assertions.assertEquals("scmUrl", entity.getScmUrl());
    }

    @Test
    public void whenEntityHasScmBranchThenReturnScmBranch() {


        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setScmBranch("scmBranch");

        Assertions.assertEquals("scmBranch", entity.getScmBranch());
    }
}
