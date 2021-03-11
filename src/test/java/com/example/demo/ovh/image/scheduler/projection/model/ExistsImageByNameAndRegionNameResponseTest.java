package com.example.demo.ovh.image.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsImageByNameAndRegionNameResponseTest {

    @Test
    public void whenModelHasExistsThenReturnExists() {

        ExistsImageByNameAndRegionNameResponse model = new ExistsImageByNameAndRegionNameResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsImageByNameAndRegionNameResponse model = model();

        String expected = "ExistsImageByNameAndRegionNameResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsImageByNameAndRegionNameResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsImageByNameAndRegionNameResponse model1 = model();
        ExistsImageByNameAndRegionNameResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsImageByNameAndRegionNameResponse model = model();

        Assertions.assertNotEquals(model, new ExistsImageByNameAndRegionNameResponse(false));
    }

    private ExistsImageByNameAndRegionNameResponse model() {

        return new ExistsImageByNameAndRegionNameResponse(true);
    }
}
