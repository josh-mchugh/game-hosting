package com.example.demo.framework.seed.ovh.region.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyRegionResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyRegionResponse model = new ExistsAnyRegionResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyRegionResponse model = model();

        String expected = "ExistsAnyRegionResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyRegionResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyRegionResponse model1 = model();
        ExistsAnyRegionResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyRegionResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyRegionResponse(false));
    }

    private ExistsAnyRegionResponse model() {

        return new ExistsAnyRegionResponse(true);
    }
}
