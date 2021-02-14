package com.example.demo.ovh.image.entity;

import com.example.demo.ovh.region.entity.RegionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ImageEntity entity = new ImageEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        ImageEntity entity = new ImageEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime now = LocalDateTime.now();

        ImageEntity entity = new ImageEntity();
        entity.setCreatedDate(now);

        Assertions.assertEquals(now, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        ImageEntity entity = new ImageEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime now = LocalDateTime.now();

        ImageEntity entity = new ImageEntity();
        entity.setLastModifiedDate(now);

        Assertions.assertEquals(now, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        ImageEntity entity = new ImageEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasRegionEntityThenReturnRegionEntity() {

        RegionEntity regionEntity = new RegionEntity();

        ImageEntity entity = new ImageEntity();
        entity.setRegionEntity(regionEntity);

        Assertions.assertNotNull(entity.getRegionEntity());
    }

    @Test
    public void whenEntityHasImageIdThenReturnImageId() {

        ImageEntity entity = new ImageEntity();
        entity.setOvhId("ovhId");

        Assertions.assertEquals("ovhId", entity.getOvhId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        ImageEntity entity = new ImageEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        ImageEntity entity= new ImageEntity();
        entity.setType("type");

        Assertions.assertEquals("type", entity.getType());
    }

    @Test
    public void whenEntityHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime now = LocalDateTime.now();

        ImageEntity entity = new ImageEntity();
        entity.setImageCreatedDate(now);

        Assertions.assertEquals(now, entity.getImageCreatedDate());
    }

    @Test
    public void whenEntityHasFlavorTypeThenReturnFlavorType() {

        ImageEntity entity = new ImageEntity();
        entity.setFlavorType("flavor-type");

        Assertions.assertEquals("flavor-type", entity.getFlavorType());
    }

    @Test
    public void whenEntityHasHourlyThenReturnHourly() {

        ImageEntity entity = new ImageEntity();
        entity.setHourly("hourly");

        Assertions.assertEquals("hourly", entity.getHourly());
    }

    @Test
    public void whenEntityHasMonthlyThenReturnMonthly() {

        ImageEntity entity = new ImageEntity();
        entity.setMonthly("monthly");

        Assertions.assertEquals("monthly", entity.getMonthly());
    }

    @Test
    public void whenEntityHasSizeThenReturnSize() {

        ImageEntity entity = new ImageEntity();
        entity.setSize(1.0D);

        Assertions.assertEquals(1.0D, entity.getSize());
    }

    @Test
    public void whenEntityHasMinRamThenReturnMinRam() {

        ImageEntity entity = new ImageEntity();
        entity.setMinRam(1);

        Assertions.assertEquals(1, entity.getMinRam());
    }

    @Test
    public void whenEntityHasMinDiskThenReturnMinDisk() {

        ImageEntity entity = new ImageEntity();
        entity.setMinDisk(1);

        Assertions.assertEquals(1, entity.getMinDisk());
    }

    @Test
    public void whenEntityHasUsernameThenReturnUsername() {

        ImageEntity entity = new ImageEntity();
        entity.setUsername("username");

        Assertions.assertEquals("username", entity.getUsername());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        ImageEntity entity = new ImageEntity();
        entity.setStatus("status");

        Assertions.assertEquals("status", entity.getStatus());
    }

    @Test
    public void whenEntityHasVisibilityThenReturnVisibility() {

        ImageEntity entity = new ImageEntity();
        entity.setVisibility("visibility");

        Assertions.assertEquals("visibility", entity.getVisibility());
    }
}
