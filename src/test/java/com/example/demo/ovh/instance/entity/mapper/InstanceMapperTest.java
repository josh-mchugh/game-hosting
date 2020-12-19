package com.example.demo.ovh.instance.entity.mapper;

import com.example.demo.ovh.instance.entity.InstanceEntity;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class InstanceMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(InstanceMapper.map((InstanceEntity) null));
    }

    @Test
    public void whenEntityIsNotNullReturnNonNullInstance() {

        Assertions.assertNotNull(InstanceMapper.map(new InstanceEntity()));
    }

    @Test
    public void whenEntityHasIdReturnId() {

        InstanceEntity instanceEntity = new InstanceEntity();
        instanceEntity.setId("id");

        Instance instance = InstanceMapper.map(instanceEntity);

        Assertions.assertEquals("id", instance.getId());
    }

    @Test
    public void whenEntityHasNullIdReturnNullId() {

        Instance instance = InstanceMapper.map(new InstanceEntity());

        Assertions.assertNull(instance.getId());
    }

    @Test
    public void whenEntityHasOvhIdReturnOvhId() {

        InstanceEntity instanceEntity = new InstanceEntity();
        instanceEntity.setOvhId("ovhId");

        Instance instance = InstanceMapper.map(instanceEntity);

        Assertions.assertEquals("ovhId", instance.getOvhId());
    }

    @Test
    public void whenEntityHasNullOvhIdReturnNull() {

        Instance instance = InstanceMapper.map(new InstanceEntity());

        Assertions.assertNull(instance.getOvhId());
    }

    @Test
    public void whenEntityHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime createdInstanceDate = LocalDateTime.now();

        InstanceEntity instanceEntity = new InstanceEntity();
        instanceEntity.setInstanceCreatedDate(createdInstanceDate);

        Instance instance = InstanceMapper.map(instanceEntity);

        Assertions.assertEquals(createdInstanceDate, instance.getInstanceCreatedDate());
    }

    @Test
    public void whenEntityHasNullInstanceCratedDateThenReturnNull() {

        Instance instance = InstanceMapper.map(new InstanceEntity());

        Assertions.assertNull(instance.getInstanceCreatedDate());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        InstanceEntity instanceEntity = new InstanceEntity();
        instanceEntity.setName("name");

        Instance instance = InstanceMapper.map(instanceEntity);

        Assertions.assertEquals("name", instance.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        Instance instance = InstanceMapper.map(new InstanceEntity());

        Assertions.assertNull(instance.getName());
    }

    @Test
    public void whenEntityHasIp4AddressThenReturnIp4Address() {

        InstanceEntity instanceEntity = new InstanceEntity();
        instanceEntity.setIp4Address("ip4-address");

        Instance instance = InstanceMapper.map(instanceEntity);

        Assertions.assertEquals("ip4-address", instance.getIp4Address());
    }

    @Test
    public void whenEntityHasNullIp4AddressThenReturnIp4Address() {

        Instance instance = InstanceMapper.map(new InstanceEntity());

        Assertions.assertNull(instance.getIp4Address());
    }

    @Test
    public void whenEntityHasIp6AddressThenReturnIp6Address() {

        InstanceEntity instanceEntity = new InstanceEntity();
        instanceEntity.setIp6Address("ip6-address");

        Instance instance = InstanceMapper.map(instanceEntity);

        Assertions.assertEquals("ip6-address", instance.getIp6Address());
    }

    @Test
    public void whenEntityHasNullIp6AddressThenReturnNull() {

        Instance instance = InstanceMapper.map(new InstanceEntity());

        Assertions.assertNull(instance.getIp6Address());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        InstanceEntity instanceEntity = new InstanceEntity();
        instanceEntity.setStatus(InstanceStatus.BUILDING);

        Instance instance = InstanceMapper.map(instanceEntity);

        Assertions.assertEquals(InstanceStatus.BUILDING, instance.getStatus());
    }

    @Test
    public void whenEntityHasNullStatusThenReturnNull() {

        Instance instance = InstanceMapper.map(new InstanceEntity());

        Assertions.assertNull(instance.getStatus());
    }

    @Test
    public void whenEntitiesHasValuesThenReturnInstances() {

        ImmutableList<InstanceEntity> instanceEntities = ImmutableList.of(new InstanceEntity());

        ImmutableList<Instance> instances = InstanceMapper.map(instanceEntities);

        Assertions.assertEquals(1, instances.size());
    }

    @Test
    public void whenEntitiesHasNullThenReturnNull() {

        ImmutableList<Instance> instances = InstanceMapper.map((ImmutableList<InstanceEntity>) null);

        Assertions.assertNull(instances);
    }

    @Test
    public void whenEntitiesHasEmptyListThenReturnNull() {

        ImmutableList<Instance> instances = InstanceMapper.map(ImmutableList.of());

        Assertions.assertNull(instances);
    }
}
