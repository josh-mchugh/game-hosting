package com.example.demo.ovh.flavor.entity;

import com.example.demo.ovh.region.entity.RegionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class FlavorEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FlavorEntity entity = new FlavorEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        FlavorEntity entity = new FlavorEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        FlavorEntity entity = new FlavorEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        FlavorEntity entity = new FlavorEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        FlavorEntity entity = new FlavorEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        FlavorEntity entity = new FlavorEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasRegionEntityThenReturnRegionEntity() {

        RegionEntity regionEntity = new RegionEntity();

        FlavorEntity entity = new FlavorEntity();
        entity.setRegionEntity(regionEntity);

        Assertions.assertEquals(regionEntity, entity.getRegionEntity());
    }

    @Test
    public void whenEntityHasOvhIdThenReturnOvhId() {

        FlavorEntity entity = new FlavorEntity();
        entity.setOvhId("ovhId");

        Assertions.assertEquals("ovhId", entity.getOvhId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        FlavorEntity entity = new FlavorEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        FlavorEntity entity = new FlavorEntity();
        entity.setType("type");

        Assertions.assertEquals("type", entity.getType());
    }

    @Test
    public void whenEntityHasAvailableThenReturnAvailable() {

        FlavorEntity entity = new FlavorEntity();
        entity.setAvailable(true);

        Assertions.assertTrue(entity.getAvailable());
    }

    @Test
    public void whenEntityHasHourlyThenReturnHourly() {

        FlavorEntity entity = new FlavorEntity();
        entity.setHourly("hourly");

        Assertions.assertEquals("hourly", entity.getHourly());
    }

    @Test
    public void whenEntityHasMonthlyThenReturnMonthly() {

        FlavorEntity entity = new FlavorEntity();
        entity.setMonthly("monthly");

        Assertions.assertEquals("monthly", entity.getMonthly());
    }

    @Test
    public void whenEntityHasQuotaThenReturnQuota() {

        FlavorEntity entity = new FlavorEntity();
        entity.setQuota(1);

        Assertions.assertEquals(1, entity.getQuota());
    }

    @Test
    public void whenEntityHasOsTypeThenReturnOsType() {

        FlavorEntity entity = new FlavorEntity();
        entity.setOsType("osType");

        Assertions.assertEquals("osType", entity.getOsType());
    }

    @Test
    public void whenEntityHasVcpusThenReturnVcpus() {

        FlavorEntity entity = new FlavorEntity();
        entity.setVcpus(1);

        Assertions.assertEquals(1, entity.getVcpus());
    }

    @Test
    public void whenEntityHasRamThenReturnRam() {

        FlavorEntity entity = new FlavorEntity();
        entity.setRam(1);

        Assertions.assertEquals(1, entity.getRam());
    }

    @Test
    public void whenEntityHasDiskThenReturnDisk() {

        FlavorEntity entity = new FlavorEntity();
        entity.setDisk(1);

        Assertions.assertEquals(1, entity.getDisk());
    }

    @Test
    public void whenEntityHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorEntity entity = new FlavorEntity();
        entity.setInboundBandwidth(1);

        Assertions.assertEquals(1, entity.getInboundBandwidth());
    }

    @Test
    public void whenEntityHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorEntity entity = new FlavorEntity();
        entity.setOutboundBandwidth(1);

        Assertions.assertEquals(1, entity.getOutboundBandwidth());
    }
}
