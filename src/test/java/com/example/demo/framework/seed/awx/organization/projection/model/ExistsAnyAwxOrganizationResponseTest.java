package com.example.demo.framework.seed.awx.organization.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxOrganizationResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyAwxOrganizationResponse model = new ExistsAnyAwxOrganizationResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyAwxOrganizationResponse model = model();

        String expected = "ExistsAnyAwxOrganizationResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxOrganizationResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxOrganizationResponse model1 = model();
        ExistsAnyAwxOrganizationResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxOrganizationResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyAwxOrganizationResponse(false));
    }

    private ExistsAnyAwxOrganizationResponse model() {

        return new ExistsAnyAwxOrganizationResponse(true);
    }
}
