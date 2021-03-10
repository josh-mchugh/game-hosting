package com.example.demo.framework.seed.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyImageResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyImageResponse model = new ExistsAnyImageResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyImageResponse model = model();

        String expected = "ExistsAnyImageResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyImageResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyImageResponse model1 = model();
        ExistsAnyImageResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyImageResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyImageResponse(false));
    }

    private ExistsAnyImageResponse model() {

        return new ExistsAnyImageResponse(true);
    }
}
