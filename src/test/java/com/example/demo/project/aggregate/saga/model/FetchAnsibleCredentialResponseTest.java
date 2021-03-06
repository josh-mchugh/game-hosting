package com.example.demo.project.aggregate.saga.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAnsibleCredentialResponseTest {

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        FetchAnsibleCredentialResponse model = new FetchAnsibleCredentialResponse("ovhId");

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelToString() {

        FetchAnsibleCredentialResponse model = model();

        String expected = "FetchAnsibleCredentialResponse(ovhId=ovhId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAnsibleCredentialResponse model = model();

        Assertions.assertEquals(106128535, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAnsibleCredentialResponse model1 = model();
        FetchAnsibleCredentialResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAnsibleCredentialResponse model = model();

        Assertions.assertNotEquals(model, new FetchAnsibleCredentialResponse(null));
    }

    private FetchAnsibleCredentialResponse model() {

        return new FetchAnsibleCredentialResponse("ovhId");
    }
}
