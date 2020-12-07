package com.example.demo.awx.organization.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAwxOrganizationIdByAwxIdQueryTest {

    @Test
    public void whenQueryHasAwxIdThenReturnAwxId() {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(1L);

        Assertions.assertEquals(1L, query.getAwxId());
    }

    @Test
    public void whenQueryToString() {

        FetchAwxOrganizationIdByAwxIdQuery query = query();

        String expected = "FetchAwxOrganizationIdByAwxIdQuery(awxId=1)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchAwxOrganizationIdByAwxIdQuery query = query();

        Assertions.assertEquals(60, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchAwxOrganizationIdByAwxIdQuery query1 = query();
        FetchAwxOrganizationIdByAwxIdQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchAwxOrganizationIdByAwxIdQuery query = query();

        Assertions.assertNotEquals(query, new FetchAwxOrganizationIdByAwxIdQuery(9999L));
    }

    private FetchAwxOrganizationIdByAwxIdQuery query() {

        return new FetchAwxOrganizationIdByAwxIdQuery(1L);
    }
}
