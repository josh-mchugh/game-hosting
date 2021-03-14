package com.example.demo.ovh.region.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsRegionByNameResponseTest {

    @Test
    public void whenModelHasExistsThenReturnExists() {

        ExistsRegionByNameResponse model = new ExistsRegionByNameResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsRegionByNameResponse model = model();

        String expected = "ExistsRegionByNameResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsRegionByNameResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsRegionByNameResponse model1 = model();
        ExistsRegionByNameResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsRegionByNameResponse model = model();

        Assertions.assertNotEquals(model, new ExistsRegionByNameResponse(false));
    }

    private ExistsRegionByNameResponse model() {

        return new ExistsRegionByNameResponse(true);
    }
}
