package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserDashboardQueryTest {

    @Test
    public void whenQueryHasEmailThenReturnEmail() {

        FetchUserDashboardQuery query = new FetchUserDashboardQuery("email");

        Assertions.assertEquals("email", query.getEmail());
    }

    @Test
    public void whenQueryToString() {

        FetchUserDashboardQuery query = query();

        String expected = "FetchUserDashboardQuery(email=email)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchUserDashboardQuery query = query();

        Assertions.assertEquals(96619479, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchUserDashboardQuery query1 = query();
        FetchUserDashboardQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchUserDashboardQuery query = query();

        Assertions.assertNotEquals(query, new FetchUserDashboardQuery("notEmail"));
    }

    private FetchUserDashboardQuery query() {

        return new FetchUserDashboardQuery("email");
    }
}
