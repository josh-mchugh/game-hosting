package com.example.demo.project.aggregate.saga.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchAnsibleCredentialResponseTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchAnsibleCredentialResponse model = new FetchAnsibleCredentialResponse(id, null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        FetchAnsibleCredentialResponse model = new FetchAnsibleCredentialResponse(null, "ovhId");

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelToString() {

        FetchAnsibleCredentialResponse model = model();

        String expected = "FetchAnsibleCredentialResponse(id=0e107c2c-ba77-4d57-a78c-cb5b18bde58b, ovhId=ovhId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAnsibleCredentialResponse model = model();

        Assertions.assertEquals(-1557348770, model.hashCode());
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

        Assertions.assertNotEquals(model, new FetchAnsibleCredentialResponse(null, null));
    }

    private FetchAnsibleCredentialResponse model() {

        return new FetchAnsibleCredentialResponse(UUID.fromString("0e107c2c-ba77-4d57-a78c-cb5b18bde58b"), "ovhId");
    }
}
