package com.example.demo.ovh.instance.entity;

import com.example.demo.project.entity.ProjectEntity;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class InstanceGroupEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntitySetUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime now = LocalDateTime.now();

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setCreatedDate(now);

        Assertions.assertEquals(now, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime now = LocalDateTime.now();

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setLastModifiedDate(now);

        Assertions.assertEquals(now, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasGroupIdThenReturnGroupId() {

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setGroupId("groupId");

        Assertions.assertEquals("groupId", entity.getGroupId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setType("type");

        Assertions.assertEquals("type", entity.getType());
    }

    @Test
    public void whenEntityHasProjectEntityThenReturnProjectEntity() {

        ProjectEntity projectEntity = new ProjectEntity();

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setProjectEntity(projectEntity);

        Assertions.assertEquals(projectEntity, entity.getProjectEntity());
    }

    @Test
    public void whenEntityHasInstanceEntitiesThenReturnInstanceEntities() {

        List<InstanceEntity> instanceEntities = Lists.newArrayList(new InstanceEntity());

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setInstanceEntities(instanceEntities);

        Assertions.assertEquals(instanceEntities, entity.getInstanceEntities());
    }
}
