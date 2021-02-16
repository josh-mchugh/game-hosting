package com.example.demo.web.admin.game.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FetchAdminGameServerRegionsResponseTest {

    @Test
    public void whenModelHasRegionsThenReturnRegions() {

        FetchAdminGameServerRegionsResponse model = new FetchAdminGameServerRegionsResponse(new ArrayList<>());

        Assertions.assertEquals(new ArrayList<>(), model.getRegions());
    }

    @Test
    public void whenModelToString() {

        FetchAdminGameServerRegionsResponse model = model();

        String expected = "FetchAdminGameServerRegionsResponse(regions=[])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminGameServerRegionsResponse model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminGameServerRegionsResponse model1 = model();
        FetchAdminGameServerRegionsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminGameServerRegionsResponse model = model();

        Assertions.assertNotEquals(model, new FetchAdminGameServerRegionsResponse(null));
    }

    private FetchAdminGameServerRegionsResponse model() {

        return new FetchAdminGameServerRegionsResponse(new ArrayList<>());
    }
}
