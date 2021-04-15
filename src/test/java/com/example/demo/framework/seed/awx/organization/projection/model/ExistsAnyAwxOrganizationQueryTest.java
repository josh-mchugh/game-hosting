package com.example.demo.framework.seed.awx.organization.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxOrganizationQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyAwxOrganizationQuery model = model();

        String expected = "ExistsAnyAwxOrganizationQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxOrganizationQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxOrganizationQuery model1 = model();
        ExistsAnyAwxOrganizationQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxOrganizationQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyAwxOrganizationQuery()));
    }

    private ExistsAnyAwxOrganizationQuery model() {

        return new ExistsAnyAwxOrganizationQuery();
    }
}
