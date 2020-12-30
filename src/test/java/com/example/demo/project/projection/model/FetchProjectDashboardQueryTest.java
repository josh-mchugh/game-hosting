package com.example.demo.project.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectDashboardQueryTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        FetchProjectDashboardQuery query = new FetchProjectDashboardQuery("email");

        Assertions.assertEquals("email", query.getEmail());
    }

    @Test
    public void whenModelToString() {

        FetchProjectDashboardQuery query = query();

        String expected = "FetchProjectDashboardQuery(email=email)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectDashboardQuery query = query();

        Assertions.assertEquals(96619479, query.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectDashboardQuery query1 = query();
        FetchProjectDashboardQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectDashboardQuery query = query();

        Assertions.assertNotEquals(query, new FetchProjectDashboardQuery(""));
    }

    private FetchProjectDashboardQuery query() {

        return new FetchProjectDashboardQuery("email");
    }
}
