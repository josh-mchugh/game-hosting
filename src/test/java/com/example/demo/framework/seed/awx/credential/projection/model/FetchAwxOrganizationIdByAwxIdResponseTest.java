package com.example.demo.framework.seed.awx.credential.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchAwxOrganizationIdByAwxIdResponseTest {

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        UUID id = UUID.randomUUID();

        FetchAwxOrganizationIdByAwxIdResponse query = new FetchAwxOrganizationIdByAwxIdResponse(id);

        Assertions.assertEquals(id, query.getId());
    }

    @Test
    public void whenModelToString() {

        FetchAwxOrganizationIdByAwxIdResponse model = model();

        String expected = "FetchAwxOrganizationIdByAwxIdResponse(id=9e2e3a49-de4e-4335-ba5b-89ef7450c7f1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAwxOrganizationIdByAwxIdResponse model = model();

        Assertions.assertEquals(-1905576035, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAwxOrganizationIdByAwxIdResponse model1 = model();
        FetchAwxOrganizationIdByAwxIdResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAwxOrganizationIdByAwxIdResponse model = model();

        Assertions.assertNotEquals(model, new FetchAwxOrganizationIdByAwxIdResponse(null));
    }

    private FetchAwxOrganizationIdByAwxIdResponse model() {

        return new FetchAwxOrganizationIdByAwxIdResponse(UUID.fromString("9e2e3a49-de4e-4335-ba5b-89ef7450c7f1"));
    }
}
