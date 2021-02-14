package com.example.demo.ovh.instance.entity;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceEntity entity = new InstanceEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        InstanceEntity entity = new InstanceEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime now = LocalDateTime.now();

        InstanceEntity entity = new InstanceEntity();
        entity.setCreatedDate(now);

        Assertions.assertEquals(now, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        InstanceEntity entity = new InstanceEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime now = LocalDateTime.now();

        InstanceEntity entity = new InstanceEntity();
        entity.setLastModifiedDate(now);

        Assertions.assertEquals(now, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        InstanceEntity entity = new InstanceEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasFlavorEntityThenReturnFlavorEntity() {

        FlavorEntity flavorEntity = new FlavorEntity();

        InstanceEntity entity = new InstanceEntity();
        entity.setFlavorEntity(flavorEntity);

        Assertions.assertEquals(flavorEntity, entity.getFlavorEntity());
    }

    @Test
    public void whenEntityHasImageEntityThenReturnImageEntity() {

        ImageEntity imageEntity = new ImageEntity();

        InstanceEntity entity = new InstanceEntity();
        entity.setImageEntity(imageEntity);

        Assertions.assertEquals(imageEntity, entity.getImageEntity());
    }

    @Test
    public void whenEntityHasInstanceGroupEntityThenReturnInstanceGroupEntity() {

        InstanceGroupEntity instanceGroupEntity = new InstanceGroupEntity();

        InstanceEntity entity = new InstanceEntity();
        entity.setInstanceGroupEntity(instanceGroupEntity);

        Assertions.assertEquals(instanceGroupEntity, entity.getInstanceGroupEntity());
    }

    @Test
    public void whenEntityHasCredentialEntityThenReturnCredentialEntity() {

        CredentialEntity credentialEntity = new CredentialEntity();

        InstanceEntity entity = new InstanceEntity();
        entity.setCredentialEntity(credentialEntity);

        Assertions.assertEquals(credentialEntity, entity.getCredentialEntity());
    }

    @Test
    public void whenEntityHasAwxHostEntityThenReturnAwxHostEntity() {

        AwxHostEntity awxHostEntity = new AwxHostEntity();

        InstanceEntity entity = new InstanceEntity();
        entity.setAwxHostEntity(awxHostEntity);

        Assertions.assertEquals(awxHostEntity, entity.getAwxHostEntity());
    }

    @Test
    public void whenEntityHasOvhIdThenReturnOvhId() {

        InstanceEntity entity = new InstanceEntity();
        entity.setOvhId("ovhId");

        Assertions.assertEquals("ovhId", entity.getOvhId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        InstanceEntity entity = new InstanceEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        InstanceEntity entity = new InstanceEntity();
        entity.setStatus(InstanceStatus.ACTIVE);

        Assertions.assertEquals(InstanceStatus.ACTIVE, entity.getStatus());
    }

    @Test
    public void whenEntityHasInstanceCreatedDateThenInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceEntity entity = new InstanceEntity();
        entity.setInstanceCreatedDate(instanceCreatedDate);

        Assertions.assertEquals(instanceCreatedDate, entity.getInstanceCreatedDate());
    }

    @Test
    public void whenEntityHasIp4AddressThenReturnIp4Address() {

        InstanceEntity entity = new InstanceEntity();
        entity.setIp4Address("ip4Address");

        Assertions.assertEquals("ip4Address", entity.getIp4Address());
    }

    @Test
    public void whenEntityHasIp6AddressThenReturnIp6Address() {

        InstanceEntity entity = new InstanceEntity();
        entity.setIp6Address("ip6Address");

        Assertions.assertEquals("ip6Address", entity.getIp6Address());
    }
}
