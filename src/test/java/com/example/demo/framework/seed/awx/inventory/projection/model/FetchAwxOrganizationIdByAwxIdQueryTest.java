package com.example.demo.framework.seed.awx.inventory.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAwxOrganizationIdByAwxIdQueryTest {

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(1L);

        Assertions.assertEquals(1L, query.getAwxId());
    }

    @Test
    public void whenModelToString() {

        FetchAwxOrganizationIdByAwxIdQuery model = model();

        String expected = "FetchAwxOrganizationIdByAwxIdQuery(awxId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAwxOrganizationIdByAwxIdQuery model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAwxOrganizationIdByAwxIdQuery model1 = model();
        FetchAwxOrganizationIdByAwxIdQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAwxOrganizationIdByAwxIdQuery model = model();

        Assertions.assertNotEquals(model, new FetchAwxOrganizationIdByAwxIdQuery(null));
    }

    private FetchAwxOrganizationIdByAwxIdQuery model() {

        return new FetchAwxOrganizationIdByAwxIdQuery(1L);
    }
}
