package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchUserIdByEmailProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchUserIdByEmailProjection projection = new FetchUserIdByEmailProjection(id.toString());

        Assertions.assertEquals(id, projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchUserIdByEmailProjection projection = projection();

        String expected = "FetchUserIdByEmailProjection(id=b7bc76ae-1ea6-4a0e-96a4-d6cfc56e4c95)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchUserIdByEmailProjection projection = projection();

        Assertions.assertEquals(-86989003, projection.hashCode());
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

        Assertions.assertNotEquals(projection, new FetchUserIdByEmailProjection(UUID.randomUUID().toString()));
    }

    private FetchUserIdByEmailProjection projection() {

        return new FetchUserIdByEmailProjection("b7bc76ae-1ea6-4a0e-96a4-d6cfc56e4c95");
    }
}
