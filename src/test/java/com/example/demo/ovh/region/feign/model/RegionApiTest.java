package com.example.demo.ovh.region.feign.model;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RegionApiTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        RegionApi model = new RegionApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasContinentCodeThenReturnContinentCode() {

        RegionApi model = new RegionApi();
        model.setContinentCode("continentCode");

        Assertions.assertEquals("continentCode", model.getContinentCode());
    }

    @Test
    public void whenModelHasDataCenterLocationThenReturnDataCenterLocation() {

        RegionApi model = new RegionApi();
        model.setDataCenterLocation("dataCenterLocation");

        Assertions.assertEquals("dataCenterLocation", model.getDataCenterLocation());
    }

    @Test
    public void whenModelHasIpCountriesThenReturnIpCountries() {

        RegionApi model = new RegionApi();
        model.setIpCountries(Arrays.asList("us", "ca", "uk"));

        Assertions.assertEquals(Arrays.asList("us", "ca", "uk"), model.getIpCountries());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        RegionApi model = new RegionApi();
        model.setStatus(RegionStatus.UP);

        Assertions.assertEquals(RegionStatus.UP, model.getStatus());
    }

    @Test
    public void whenModelToString() {

        RegionApi model = model();

        String expected = "RegionApi(name=name, continentCode=continentCode, dataCenterLocation=dataCenterLocation, ipCountries=[us, ca, uk], status=UP)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        RegionApi model = new RegionApi();
        model.setName("name");
        model.setContinentCode("continentCode");
        model.setDataCenterLocation("dataCenterLocation");
        model.setIpCountries(Arrays.asList("us", "ca", "uk"));

        Assertions.assertEquals(2098179935, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        RegionApi model1 = model();
        RegionApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        RegionApi model = model();

        Assertions.assertNotEquals(model, new RegionApi());
    }

    private RegionApi model() {

        RegionApi model = new RegionApi();
        model.setName("name");
        model.setContinentCode("continentCode");
        model.setDataCenterLocation("dataCenterLocation");
        model.setIpCountries(Arrays.asList("us", "ca", "uk"));
        model.setStatus(RegionStatus.UP);

        return model;
    }
}
