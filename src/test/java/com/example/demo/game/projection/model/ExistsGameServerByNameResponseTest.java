package com.example.demo.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsGameServerByNameResponseTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        ExistsGameServerByNameResponse model = new ExistsGameServerByNameResponse(true);

        Assertions.assertTrue(model.isExists());
    }

    @Test
    public void whenModelToString() {

        ExistsGameServerByNameResponse model = model();

        String expected = "ExistsGameServerByNameResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsGameServerByNameResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsGameServerByNameResponse model1 = model();
        ExistsGameServerByNameResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsGameServerByNameResponse model = model();

        Assertions.assertNotEquals(model, new ExistsGameServerByNameResponse(false));
    }

    private ExistsGameServerByNameResponse model() {

        return new ExistsGameServerByNameResponse(true);
    }
}
