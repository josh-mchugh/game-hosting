package com.example.demo.framework.seed.awx.inventory.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxInventoryQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyAwxInventoryQuery model = model();

        String expected = "ExistsAnyAwxInventoryQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxInventoryQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxInventoryQuery model1 = model();
        ExistsAnyAwxInventoryQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxInventoryQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyAwxInventoryQuery()));
    }

    private ExistsAnyAwxInventoryQuery model() {

        return new ExistsAnyAwxInventoryQuery();
    }
}
