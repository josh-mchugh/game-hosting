package com.example.demo.web.awx.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyPlaybooksResponseTest {

    @Test
    public void whenModelHasExistsThenExpectExists() {

        ExistsAnyPlaybooksResponse model = new ExistsAnyPlaybooksResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyPlaybooksResponse model = model();

        String expected = "ExistsAnyPlaybooksResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyPlaybooksResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyPlaybooksResponse model1 = model();
        ExistsAnyPlaybooksResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyPlaybooksResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyPlaybooksResponse(false));
    }

    private ExistsAnyPlaybooksResponse model() {

        return new ExistsAnyPlaybooksResponse(true);
    }
}
