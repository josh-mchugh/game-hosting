package com.example.demo.web.awx.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyPlaybooksQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyPlaybooksQuery model = model();

        String expected = "ExistsAnyPlaybooksQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyPlaybooksQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyPlaybooksQuery model1 = model();
        ExistsAnyPlaybooksQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyPlaybooksQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyPlaybooksQuery()));
    }

    private ExistsAnyPlaybooksQuery model() {

        return new ExistsAnyPlaybooksQuery();
    }
}
