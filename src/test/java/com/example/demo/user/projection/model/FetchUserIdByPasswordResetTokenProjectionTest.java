package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserIdByPasswordResetTokenProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        FetchUserIdByPasswordResetTokenProjection projection = new FetchUserIdByPasswordResetTokenProjection("id");

        Assertions.assertEquals("id", projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchUserIdByPasswordResetTokenProjection projection = projection();

        String expected = "FetchUserIdByPasswordResetTokenProjection(id=id)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchUserIdByPasswordResetTokenProjection projection = projection();

        Assertions.assertEquals(3414, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchUserIdByPasswordResetTokenProjection projection1 = projection();
        FetchUserIdByPasswordResetTokenProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchUserIdByPasswordResetTokenProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchUserIdByPasswordResetTokenProjection("otherId"));
    }

    private FetchUserIdByPasswordResetTokenProjection projection() {

        return new FetchUserIdByPasswordResetTokenProjection("id");
    }
}
