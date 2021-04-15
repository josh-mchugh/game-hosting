package com.example.demo.framework.seed.awx.inventory.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxInventoryResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyAwxInventoryResponse model = new ExistsAnyAwxInventoryResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyAwxInventoryResponse model = model();

        String expected = "ExistsAnyAwxInventoryResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxInventoryResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxInventoryResponse model1 = model();
        ExistsAnyAwxInventoryResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxInventoryResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyAwxInventoryResponse(false));
    }

    private ExistsAnyAwxInventoryResponse model() {

        return new ExistsAnyAwxInventoryResponse(true);
    }
}
