package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchUserIdByVerificationTokenProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchUserIdByVerificationTokenProjection projection = new FetchUserIdByVerificationTokenProjection(id.toString());

        Assertions.assertEquals(id, projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchUserIdByVerificationTokenProjection projection = projection();

        String expected = "FetchUserIdByVerificationTokenProjection(id=0109b10f-3593-40a1-9ae1-93a48ca4db7b)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchUserIdByVerificationTokenProjection projection = projection();

        Assertions.assertEquals(585087404, projection.hashCode());
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

        Assertions.assertNotEquals(projection, new FetchUserIdByVerificationTokenProjection(UUID.randomUUID().toString()));
    }

    private FetchUserIdByVerificationTokenProjection projection() {

        return new FetchUserIdByVerificationTokenProjection("0109b10f-3593-40a1-9ae1-93a48ca4db7b");
    }
}
