package com.example.demo.framework.seed.awx.project.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxProjectResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyAwxProjectResponse model = new ExistsAnyAwxProjectResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyAwxProjectResponse model = model();

        String expected = "ExistsAnyAwxProjectResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxProjectResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxProjectResponse model1 = model();
        ExistsAnyAwxProjectResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxProjectResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyAwxProjectResponse(false));
    }

    private ExistsAnyAwxProjectResponse model() {

        return new ExistsAnyAwxProjectResponse(true);
    }
}
