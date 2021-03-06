package com.example.demo.framework.seed.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyFlavorResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyFlavorResponse model = new ExistsAnyFlavorResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyFlavorResponse model = model();

        String expected = "ExistsAnyFlavorResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyFlavorResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyFlavorResponse model1 = model();
        ExistsAnyFlavorResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyFlavorResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyFlavorResponse(false));
    }

    private ExistsAnyFlavorResponse model() {

        return new ExistsAnyFlavorResponse(true);
    }
}
