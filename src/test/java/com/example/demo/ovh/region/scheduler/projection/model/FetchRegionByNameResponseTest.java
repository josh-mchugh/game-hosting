package com.example.demo.ovh.region.scheduler.projection.model;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.scheduler.projection.projection.RegionProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchRegionByNameResponseTest {

    @Test
    public void whenModelHasRegionThenReturnRegion() {

        FetchRegionByNameResponse model = new FetchRegionByNameResponse(projection());

        Assertions.assertEquals(projection(), model.getRegion());
    }

    @Test
    public void whenModelToString() {

        FetchRegionByNameResponse model = model();

        String expected = "FetchRegionByNameResponse(region=RegionProjection(id=17035ec2-c08e-41b1-98b2-032190e24560, continentCode=continentCode, countryCodes=countryCode, dataCenterLocation=dataCenterLocation, status=UP))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        RegionProjection projection = new RegionProjection(UUID.fromString("17035ec2-c08e-41b1-98b2-032190e24560").toString(), "continentCode", "countryCode", "dataCenterLocation", null);
        FetchRegionByNameResponse model = new FetchRegionByNameResponse(projection);

        Assertions.assertEquals(1814042157, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchRegionByNameResponse model1 = model();
        FetchRegionByNameResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchRegionByNameResponse model = model();

        Assertions.assertNotEquals(model, new FetchRegionByNameResponse(null));
    }

    private FetchRegionByNameResponse model() {

        return new FetchRegionByNameResponse(projection());
    }

    private RegionProjection projection() {

        return new RegionProjection(UUID.fromString("17035ec2-c08e-41b1-98b2-032190e24560").toString(), "continentCode", "countryCode", "dataCenterLocation", RegionStatus.UP);
    }
}
