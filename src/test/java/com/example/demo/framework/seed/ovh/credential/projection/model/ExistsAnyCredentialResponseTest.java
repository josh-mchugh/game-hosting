package com.example.demo.framework.seed.ovh.credential.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyCredentialResponseTest {

    @Test
    public void whenModelHasExistThenReturnExists() {

        ExistsAnyCredentialResponse model = new ExistsAnyCredentialResponse(false);

        Assertions.assertFalse(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsAnyCredentialResponse model = model();

        String expected = "ExistsAnyCredentialResponse(exists=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyCredentialResponse model = model();

        Assertions.assertEquals(138, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyCredentialResponse model1 = model();
        ExistsAnyCredentialResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyCredentialResponse model = model();

        Assertions.assertNotEquals(model, new ExistsAnyCredentialResponse(false));
    }

    private ExistsAnyCredentialResponse model() {

        return new ExistsAnyCredentialResponse(true);
    }
}
