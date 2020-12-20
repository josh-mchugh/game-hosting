package com.example.demo.ovh.region.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegionEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        RegionEntity entity = new RegionEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionEntity entity = new RegionEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        RegionEntity entity = new RegionEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        RegionEntity entity = new RegionEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenLastModifiedBy() {

        RegionEntity entity = new RegionEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        RegionEntity entity = new RegionEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        RegionEntity entity = new RegionEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        RegionEntity entity = new RegionEntity();
        entity.setName("name");

        Assertions.assertEquals("name", entity.getName());
    }

    @Test
    public void whenEntityHasContinentCodeThenReturnContinentCode() {

        RegionEntity entity = new RegionEntity();
        entity.setContinentCode("continentCode");

        Assertions.assertEquals("continentCode", entity.getContinentCode());
    }

    @Test
    public void whenEntityHasCountryCodesThenCountryCodes() {

        RegionEntity entity = new RegionEntity();
        entity.setCountryCodes("countryCodes");

        Assertions.assertEquals("countryCodes", entity.getCountryCodes());
    }

    @Test
    public void whenEntityHasDataCenterLocationThenReturnDataCenterLocation() {

        RegionEntity entity = new RegionEntity();
        entity.setDataCenterLocation("dataCenterLocation");

        Assertions.assertEquals("dataCenterLocation", entity.getDataCenterLocation());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        RegionEntity entity = new RegionEntity();
        entity.setStatus(RegionStatus.UP);

        Assertions.assertEquals(RegionStatus.UP, entity.getStatus());
    }
}
