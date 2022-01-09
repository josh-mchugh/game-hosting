package com.example.demo.framework.seed.awx.project.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAwxOrganizationIdByAwxIdResponseTest {

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        FetchAwxOrganizationIdByAwxIdResponse query = new FetchAwxOrganizationIdByAwxIdResponse("id");

        Assertions.assertEquals("id", query.getId());
    }

    @Test
    public void whenModelToString() {

        FetchAwxOrganizationIdByAwxIdResponse model = model();

        String expected = "FetchAwxOrganizationIdByAwxIdResponse(id=id)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAwxOrganizationIdByAwxIdResponse model = model();

        Assertions.assertEquals(3414, model.hashCode());
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

        return new FetchAwxOrganizationIdByAwxIdResponse("id");
    }
}
