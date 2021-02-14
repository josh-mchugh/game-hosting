package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchUserIdByPasswordResetTokenProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchUserIdByPasswordResetTokenProjection projection = new FetchUserIdByPasswordResetTokenProjection(id.toString());

        Assertions.assertEquals(id, projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchUserIdByPasswordResetTokenProjection projection = projection();

        String expected = "FetchUserIdByPasswordResetTokenProjection(id=e97729d3-15bf-4f91-ac40-f4e48e7a60e0)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchUserIdByPasswordResetTokenProjection projection = projection();

        Assertions.assertEquals(-554503551, projection.hashCode());
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

        Assertions.assertNotEquals(projection, new FetchUserIdByPasswordResetTokenProjection(UUID.randomUUID().toString()));
    }

    private FetchUserIdByPasswordResetTokenProjection projection() {

        return new FetchUserIdByPasswordResetTokenProjection("e97729d3-15bf-4f91-ac40-f4e48e7a60e0");
    }
}
