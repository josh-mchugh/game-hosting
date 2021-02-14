package com.example.demo.project.entity;

import com.example.demo.user.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProjectMembershipEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasProjectEntityThenReturnProjectEntity() {

        ProjectEntity projectEntity = new ProjectEntity();

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setProjectEntity(projectEntity);

        Assertions.assertEquals(projectEntity, entity.getProjectEntity());
    }

    @Test
    public void whenEntityHasUserEntityThenReturnUserEntity() {

        UserEntity userEntity = new UserEntity();

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setUserEntity(userEntity);

        Assertions.assertEquals(userEntity, entity.getUserEntity());

    }

    @Test
    public void whenEntityHasRoleThenReturnRole() {

        ProjectMembershipEntity entity = new ProjectMembershipEntity();
        entity.setRole(ProjectMembershipRole.OWNER);

        Assertions.assertEquals(ProjectMembershipRole.OWNER, entity.getRole());
    }
}
