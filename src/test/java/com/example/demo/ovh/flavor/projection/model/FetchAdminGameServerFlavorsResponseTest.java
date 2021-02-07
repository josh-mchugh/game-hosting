package com.example.demo.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FetchAdminGameServerFlavorsResponseTest {

    @Test
    public void whenModelHasFlavorsThenReturnFlavors() {

        FetchAdminGameServerFlavorsResponse model = new FetchAdminGameServerFlavorsResponse(new ArrayList<>());

        Assertions.assertEquals(new ArrayList<>(), model.getFlavors());
    }

    @Test
    public void whenModelToString() {

        FetchAdminGameServerFlavorsResponse model = model();

        String expected = "FetchAdminGameServerFlavorsResponse(flavors=[])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminGameServerFlavorsResponse model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminGameServerFlavorsResponse model1 = model();
        FetchAdminGameServerFlavorsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminGameServerFlavorsResponse model = model();

        Assertions.assertNotEquals(model, new FetchAdminGameServerFlavorsResponse(null));
    }

    private FetchAdminGameServerFlavorsResponse model() {

        return new FetchAdminGameServerFlavorsResponse(new ArrayList<>());
    }
}
