package com.example.demo.awx.playbook.entity;

import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.project.entity.ProjectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class AwxPlayBookEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectEntity entity = new ProjectEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
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
    public void whenEntityHasAwxProjectEntityThenReturnAwxProjectEntity() {

        AwxProjectEntity projectEntity = new AwxProjectEntity();

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setAwxProjectEntity(projectEntity);

        Assertions.assertEquals(projectEntity, entity.getAwxProjectEntity());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setType(PlaybookType.TEST);

        Assertions.assertEquals(PlaybookType.TEST, entity.getType());
    }
}
