package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByEmailQueryTest {

    @Test
    public void whenQueryHasEmailThenReturnEmail() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery("test@test");

        Assertions.assertEquals("test@test", query.getEmail());
    }

    @Test
    public void whenQueryToString() {

        FetchUserIdByEmailQuery query = query();

        String expect = "FetchUserIdByEmailQuery(email=test@test)";

        Assertions.assertEquals(expect, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchUserIdByEmailQuery query = query();

        Assertions.assertEquals(-1208751173, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchUserIdByEmailQuery query1 = query();
        FetchUserIdByEmailQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchUserIdByEmailQuery query = query();

        Assertions.assertNotEquals(query, new FetchUserIdByEmailQuery("email@email"));
    }

    private FetchUserIdByEmailQuery query() {

        return new FetchUserIdByEmailQuery("test@test");
    }
}
