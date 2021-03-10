package com.example.demo.framework.seed.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyImageQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyImageQuery model = model();

        String expected = "ExistsAnyImageQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyImageQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyImageQuery model1 = model();
        ExistsAnyImageQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyImageQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyImageQuery()));
    }

    private ExistsAnyImageQuery model() {

        return new ExistsAnyImageQuery();
    }
}
