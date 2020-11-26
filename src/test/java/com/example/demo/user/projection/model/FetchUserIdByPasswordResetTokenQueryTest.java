package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByPasswordResetTokenQueryTest {

    @Test
    public void whenQueryHasTokeThenReturnToken() {

        FetchUserIdByPasswordResetTokenQuery query = new FetchUserIdByPasswordResetTokenQuery("token");

        Assertions.assertEquals("token", query.getToken());
    }

    @Test
    public void whenQueryToString() {

        FetchUserIdByPasswordResetTokenQuery query = query();

        String expected = "FetchUserIdByPasswordResetTokenQuery(token=token)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchUserIdByPasswordResetTokenQuery query = query();

        Assertions.assertEquals(110541364, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchUserIdByPasswordResetTokenQuery query1 = query();
        FetchUserIdByPasswordResetTokenQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchUserIdByPasswordResetTokenQuery query = query();

        Assertions.assertNotEquals(query, new FetchUserIdByPasswordResetTokenQuery("otherId"));
    }

    private FetchUserIdByPasswordResetTokenQuery query() {

        return new FetchUserIdByPasswordResetTokenQuery("token");
    }
}
