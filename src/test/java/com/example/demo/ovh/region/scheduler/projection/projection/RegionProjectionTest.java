package com.example.demo.ovh.region.scheduler.projection.projection;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionProjection model = new RegionProjection(id.toString(), null, null, null, null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasContinentCodeThenReturnContinentCode() {

        RegionProjection model = new RegionProjection(UUID.randomUUID().toString(), "continentCode", null, null, null);

        Assertions.assertEquals("continentCode", model.getContinentCode());
    }

    @Test
    public void whenModelHasCountryCodesThenReturnCountryCodes() {

        RegionProjection model = new RegionProjection(UUID.randomUUID().toString(), null, "countryCodes", null, null);

        Assertions.assertEquals("countryCodes", model.getCountryCodes());
    }

    @Test
    public void whenModelHasDataCenterLocationThenReturnDataCenterLocation() {

        RegionProjection model = new RegionProjection(UUID.randomUUID().toString(), null, null, "dataCenterLocation", null);

        Assertions.assertEquals("dataCenterLocation", model.getDataCenterLocation());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        RegionProjection model = new RegionProjection(UUID.randomUUID().toString(), null, null, null, RegionStatus.UP);

        Assertions.assertEquals(RegionStatus.UP, model.getStatus());
    }

    @Test
    public void whenModelToString() {

        RegionProjection model = model();

        String expected = "RegionProjection(id=17035ec2-c08e-41b1-98b2-032190e24560, continentCode=continentCode, countryCodes=countryCode, dataCenterLocation=dataCenterLocation, status=UP)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        RegionProjection model = new RegionProjection(UUID.fromString("17035ec2-c08e-41b1-98b2-032190e24560").toString(), "continentCode", "countryCode", "dataCenterLocation", null);

        Assertions.assertEquals(1814042098, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        RegionProjection model1 = model();
        RegionProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        RegionProjection model = model();

        Assertions.assertNotEquals(model, new RegionProjection(UUID.randomUUID().toString(), null, null, null, null));
    }

    private RegionProjection model() {

        return new RegionProjection(UUID.fromString("17035ec2-c08e-41b1-98b2-032190e24560").toString(), "continentCode", "countryCode", "dataCenterLocation", RegionStatus.UP);
    }
}
