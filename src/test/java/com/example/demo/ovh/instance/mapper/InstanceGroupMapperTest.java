package com.example.demo.ovh.instance.mapper;

import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.example.demo.ovh.instance.model.InstanceGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstanceGroupMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(InstanceGroupMapper.map(null));
    }

    @Test
    public void whenEntityIsNotNullThenReturnInstance() {

        Assertions.assertNotNull(InstanceGroupMapper.map(new InstanceGroupEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        InstanceGroupEntity instanceGroupEntity = new InstanceGroupEntity();
        instanceGroupEntity.setId("id");

        InstanceGroup instanceGroup = InstanceGroupMapper.map(instanceGroupEntity);

        Assertions.assertEquals("id", instanceGroup.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        InstanceGroup instanceGroup = InstanceGroupMapper.map(new InstanceGroupEntity());

        Assertions.assertNull(instanceGroup.getId());
    }

    @Test
    public void whenEntityHasGroupIdThenReturnGroupId() {

        InstanceGroupEntity instanceGroupEntity = new InstanceGroupEntity();
        instanceGroupEntity.setGroupId("group-id");

        InstanceGroup instanceGroup = InstanceGroupMapper.map(instanceGroupEntity);

        Assertions.assertEquals("group-id", instanceGroup.getGroupId());
    }

    @Test
    public void whenEntityHasNullGroupIdThenReturnNull() {

        InstanceGroup instanceGroup = InstanceGroupMapper.map(new InstanceGroupEntity());

        Assertions.assertNull(instanceGroup.getGroupId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        InstanceGroupEntity instanceGroupEntity = new InstanceGroupEntity();
        instanceGroupEntity.setName("name");

        InstanceGroup instanceGroup = InstanceGroupMapper.map(instanceGroupEntity);

        Assertions.assertEquals("name", instanceGroup.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        InstanceGroup instanceGroup = InstanceGroupMapper.map(new InstanceGroupEntity());

        Assertions.assertNull(instanceGroup.getName());
    }

    @Test
    public void whenTestEntityHasTypeThenReturnType() {

        InstanceGroupEntity instanceGroupEntity = new InstanceGroupEntity();
        instanceGroupEntity.setType("type");

        InstanceGroup instanceGroup = InstanceGroupMapper.map(instanceGroupEntity);

        Assertions.assertEquals("type", instanceGroup.getType());
    }

    @Test
    public void whenTestEntityHasNullTypeThenReturnNull() {

        InstanceGroup instanceGroup = InstanceGroupMapper.map(new InstanceGroupEntity());

        Assertions.assertNull(instanceGroup.getType());
    }
}
