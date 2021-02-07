package com.example.demo.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsGameServerByConfigResponseTest {

    @Test
    public void whenModelHasExistsThenReturnExists() {

        ExistsGameServerByConfigResponse model = new ExistsGameServerByConfigResponse(true);

        Assertions.assertTrue(model.isExists());
    }

    @Test
    public void whenModelToString() {

        ExistsGameServerByConfigResponse model = model();

        String expected = "ExistsGameServerByConfigResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsGameServerByConfigResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsGameServerByConfigResponse model1 = model();
        ExistsGameServerByConfigResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsGameServerByConfigResponse model = model();

        Assertions.assertNotEquals(model, new ExistsGameServerByConfigResponse(false));
    }

    private ExistsGameServerByConfigResponse model() {

        return new ExistsGameServerByConfigResponse(true);
    }
}
