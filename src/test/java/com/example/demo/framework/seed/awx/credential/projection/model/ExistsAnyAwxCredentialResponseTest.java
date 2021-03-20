package com.example.demo.framework.seed.awx.credential.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxCredentialResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyAwxCredentialResponse model = new ExistsAnyAwxCredentialResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyAwxCredentialResponse model = model();

        String expected = "ExistsAnyAwxCredentialResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxCredentialResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxCredentialResponse model1 = model();
        ExistsAnyAwxCredentialResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxCredentialResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyAwxCredentialResponse(false));
    }

    private ExistsAnyAwxCredentialResponse model() {

        return new ExistsAnyAwxCredentialResponse(true);
    }
}
