package com.example.demo.ovh.flavor.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsFlavorByOvhIdResponseTest {

    @Test
    public void whenModelHasExistsThenReturnExists() {

        ExistsFlavorByOvhIdResponse model = new ExistsFlavorByOvhIdResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsFlavorByOvhIdResponse model = model();

        String expected = "ExistsFlavorByOvhIdResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsFlavorByOvhIdResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsFlavorByOvhIdResponse model1 = model();
        ExistsFlavorByOvhIdResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsFlavorByOvhIdResponse model = model();

        Assertions.assertNotEquals(model, new ExistsFlavorByOvhIdResponse(false));
    }

    private ExistsFlavorByOvhIdResponse model() {

        return new ExistsFlavorByOvhIdResponse(true);
    }
}
