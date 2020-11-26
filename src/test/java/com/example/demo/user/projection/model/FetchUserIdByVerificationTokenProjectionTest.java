package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByVerificationTokenProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        FetchUserIdByVerificationTokenProjection projection = new FetchUserIdByVerificationTokenProjection("id");

        Assertions.assertEquals("id", projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchUserIdByVerificationTokenProjection projection = projection();

        String expected = "FetchUserIdByVerificationTokenProjection(id=id)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchUserIdByVerificationTokenProjection projection = projection();

        Assertions.assertEquals(3414, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchUserIdByVerificationTokenProjection projection1 = projection();
        FetchUserIdByVerificationTokenProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchUserIdByVerificationTokenProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchUserIdByVerificationTokenProjection("otherId"));
    }

    private FetchUserIdByVerificationTokenProjection projection() {

        return new FetchUserIdByVerificationTokenProjection("id");
    }
}
