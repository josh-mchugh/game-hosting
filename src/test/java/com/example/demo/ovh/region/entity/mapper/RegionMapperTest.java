package com.example.demo.ovh.region.entity.mapper;

import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.entity.model.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(RegionMapper.map(null));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionEntity entity = new RegionEntity();
        entity.setId(id);

        Region region = RegionMapper.map(entity);

        Assertions.assertEquals(id.toString(), region.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        Region region = RegionMapper.map(new RegionEntity());

        Assertions.assertNull(region.getId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        RegionEntity entity = new RegionEntity();
        entity.setName("name");

        Region region = RegionMapper.map(entity);

        Assertions.assertEquals("name", region.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        Region region = RegionMapper.map(new RegionEntity());

        Assertions.assertNull(region.getName());
    }

    @Test
    public void whenEntityHasContinentCodeThenReturnContinentCode() {

        RegionEntity entity = new RegionEntity();
        entity.setContinentCode("continent code");

        Region region = RegionMapper.map(entity);

        Assertions.assertEquals("continent code", region.getContinentCode());
    }

    @Test
    public void whenEntityHasNullContinentCodeThenReturnNull() {

        Region region = RegionMapper.map(new RegionEntity());

        Assertions.assertNull(region.getContinentCode());
    }

    @Test
    public void whenEntityHasCountryCodesThenReturnCountryCodes() {

        RegionEntity entity = new RegionEntity();
        entity.setCountryCodes("country codes");

        Region region = RegionMapper.map(entity);

        Assertions.assertEquals("country codes", region.getCountryCodes());
    }

    @Test
    public void whenEntityHasNullCountryCodesThenReturnNull() {

        Region region = RegionMapper.map(new RegionEntity());

        Assertions.assertNull(region.getCountryCodes());
    }

    @Test
    public void whenEntityHasDatacenterLocationThenReturnDatacenterLocation() {

        RegionEntity entity = new RegionEntity();
        entity.setDataCenterLocation("datacenter location");

        Region region = RegionMapper.map(entity);

        Assertions.assertEquals("datacenter location", region.getDataCenterLocation());
    }

    @Test
    public void whenEntityHasNullDatacenterLocationThenReturnNull() {

        Region region = RegionMapper.map(new RegionEntity());

        Assertions.assertNull(region.getDataCenterLocation());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        RegionEntity entity = new RegionEntity();
        entity.setStatus(RegionStatus.UP);

        Region region = RegionMapper.map(entity);

        Assertions.assertEquals(RegionStatus.UP, region.getStatus());
    }

    @Test
    public void whenEntityHasNullStatusThenReturnNull() {

        Region region = RegionMapper.map(new RegionEntity());

        Assertions.assertNull(region.getStatus());
    }
}
