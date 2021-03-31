package com.example.demo.framework.seed.awx.project.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxProjectQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyAwxProjectQuery model = model();

        String expected = "ExistsAnyAwxProjectQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxProjectQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxProjectQuery model1 = model();
        ExistsAnyAwxProjectQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxProjectQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyAwxProjectQuery()));
    }

    private ExistsAnyAwxProjectQuery model() {

        return new ExistsAnyAwxProjectQuery();
    }
}
