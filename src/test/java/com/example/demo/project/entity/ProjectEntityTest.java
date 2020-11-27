package com.example.demo.project.entity;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectEntityTest {

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
    public void whenEntityHasNameThenReturnName() {

        ProjectEntity entity = new ProjectEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        ProjectEntity entity = new ProjectEntity();
        entity.setStatus(ProjectStatus.ACTIVE);

        Assertions.assertEquals(ProjectStatus.ACTIVE, entity.getStatus());
    }

    @Test
    public void whenEntityHasStateThenReturnState() {

        ProjectEntity entity = new ProjectEntity();
        entity.setState(ProjectState.ACTIVE);

        Assertions.assertEquals(ProjectState.ACTIVE, entity.getState());
    }

    @Test
    public void whenEntityHasGameEntityThenReturnGameEntity() {

        GameEntity gameEntity = new GameEntity();

        ProjectEntity entity = new ProjectEntity();
        entity.setGameEntity(gameEntity);

        Assertions.assertEquals(gameEntity, entity.getGameEntity());
    }

    @Test
    public void whenEntityHasInstanceGroupEntityThenReturnInstanceGroupEntity() {

        InstanceGroupEntity instanceGroupEntity = new InstanceGroupEntity();

        ProjectEntity entity = new ProjectEntity();
        entity.setInstanceGroupEntity(instanceGroupEntity);

        Assertions.assertEquals(instanceGroupEntity, entity.getInstanceGroupEntity());
    }

    @Test
    public void whenEntityHasProjectMembershipEntitiesThenReturnProjectMembershipEntities() {

        List<ProjectMembershipEntity> projectMembershipEntities = Lists.newArrayList(new ProjectMembershipEntity());

        ProjectEntity entity = new ProjectEntity();
        entity.setProjectMembershipsEntities(projectMembershipEntities);

        Assertions.assertEquals(projectMembershipEntities, entity.getProjectMembershipsEntities());
    }
}
