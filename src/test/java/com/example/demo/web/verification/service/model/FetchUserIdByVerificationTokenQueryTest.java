package com.example.demo.web.verification.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByVerificationTokenQueryTest {

    @Test
    public void whenQueryHasTokenThenReturnToken() {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery("token");

        Assertions.assertEquals("token", query.getToken());
    }

    @Test
    public void whenQueryToString() {

        FetchUserIdByVerificationTokenQuery query = query();

        String expected = "FetchUserIdByVerificationTokenQuery(token=token)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchUserIdByVerificationTokenQuery query = query();

        Assertions.assertEquals(110541364, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchUserIdByVerificationTokenQuery query1 = query();
        FetchUserIdByVerificationTokenQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchUserIdByVerificationTokenQuery query = query();

        Assertions.assertNotEquals(query, new FetchUserIdByVerificationTokenQuery("otherId"));
    }

    private FetchUserIdByVerificationTokenQuery query() {

        return new FetchUserIdByVerificationTokenQuery("token");
    }
}
