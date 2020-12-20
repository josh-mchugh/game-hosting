package com.example.demo.ovh.region.entity.model;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        Region model = Region.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        Region model = Region.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasContinentCodeThenReturnContinentCode() {

        Region model = Region.builder()
                .continentCode("continentCode")
                .build();

        Assertions.assertEquals("continentCode", model.getContinentCode());
    }

    @Test
    public void whenModelHasCountryCodesThenReturnCountryCodes() {

        Region model = Region.builder()
                .countryCodes("countryCodes")
                .build();

        Assertions.assertEquals("countryCodes", model.getCountryCodes());
    }

    @Test
    public void whenModelHasDataCenterLocationThenReturnDataCenterLocation() {

        Region model = Region.builder()
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals("dataCenterLocation", model.getDataCenterLocation());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        Region model = Region.builder()
                .status(RegionStatus.UP)
                .build();

        Assertions.assertEquals(RegionStatus.UP, model.getStatus());
    }

    @Test
    public void whenModelToString() {

        Region model = model();

        String expected = "Region(id=id, name=name, continentCode=continentCode, countryCodes=countryCodes, dataCenterLocation=dataCenterLocation, status=UP)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        Region model = Region.builder()
                .id("id")
                .name("name")
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals(-1408749785, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        Region model1 = model();
        Region model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        Region model = model();

        Assertions.assertNotEquals(model, Region.builder().build());
    }

    private Region model() {

        return Region.builder()
                .id("id")
                .name("name")
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .status(RegionStatus.UP)
                .build();
    }
}
