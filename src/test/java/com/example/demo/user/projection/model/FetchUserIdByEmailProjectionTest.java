package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByEmailProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        FetchUserIdByEmailProjection projection = new FetchUserIdByEmailProjection("id");

        Assertions.assertEquals("id", projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchUserIdByEmailProjection projection = projection();

        String expected = "FetchUserIdByEmailProjection(id=id)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchUserIdByEmailProjection projection = projection();

        Assertions.assertEquals(3414, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchUserIdByEmailProjection projection1 = projection();
        FetchUserIdByEmailProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchUserIdByEmailProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchUserIdByEmailProjection("otherId"));
    }

    private FetchUserIdByEmailProjection projection() {

        return new FetchUserIdByEmailProjection("id");
    }
}
