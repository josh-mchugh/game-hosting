package com.example.demo.ovh.flavor.scheduler.projection.model;

import com.example.demo.ovh.flavor.scheduler.projection.projection.FlavorProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchFlavorByOvhIdResponseTest {

    @Test
    public void whenModelHasFlavorThenReturnFlavor() {

        FetchFlavorByOvhIdResponse model = new FetchFlavorByOvhIdResponse(projection());

        Assertions.assertEquals(projection(), model.getFlavor());
    }

    @Test
    public void whenModelToString() {

        FetchFlavorByOvhIdResponse model = model();

        String expected = "FetchFlavorByOvhIdResponse(flavor=FlavorProjection(id=bc24440a-0e14-4ee0-8905-bcadb2d954cd, name=name, type=type, available=true, hourly=hourly, monthly=monthly, quota=1, osType=osType, vcpus=1, ram=1, disk=1, inboundBandwidth=1, outboundBandwidth=1))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchFlavorByOvhIdResponse model = model();

        Assertions.assertEquals(-1813506604, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchFlavorByOvhIdResponse model1 = model();
        FetchFlavorByOvhIdResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchFlavorByOvhIdResponse model = model();

        Assertions.assertNotEquals(model, new FetchFlavorByOvhIdResponse(null));
    }

    private FetchFlavorByOvhIdResponse model() {

        return new FetchFlavorByOvhIdResponse(projection());
    }

    private FlavorProjection projection() {

        return new FlavorProjection(UUID.fromString("bc24440a-0e14-4ee0-8905-bcadb2d954cd").toString(), "name", "type", true, "hourly", "monthly", 1, "osType", 1, 1, 1, 1, 1);
    }
}
