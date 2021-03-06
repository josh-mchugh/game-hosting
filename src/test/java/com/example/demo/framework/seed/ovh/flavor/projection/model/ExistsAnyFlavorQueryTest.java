package com.example.demo.framework.seed.ovh.flavor.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyFlavorQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyFlavorQuery model = model();

        String expected = "ExistsAnyFlavorQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyFlavorQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyFlavorQuery model1 = model();
        ExistsAnyFlavorQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyFlavorQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyFlavorQuery()));
    }

    private ExistsAnyFlavorQuery model() {

        return new ExistsAnyFlavorQuery();
    }
}
