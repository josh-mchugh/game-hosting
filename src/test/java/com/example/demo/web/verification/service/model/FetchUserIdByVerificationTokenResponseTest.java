package com.example.demo.web.verification.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchUserIdByVerificationTokenResponseTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchUserIdByVerificationTokenResponse projection = new FetchUserIdByVerificationTokenResponse(id);

        Assertions.assertEquals(id, projection.getId());
    }

    @Test
    public void whenProjectionToString() {

        FetchUserIdByVerificationTokenResponse projection = projection();

        String expected = "FetchUserIdByVerificationTokenResponse(id=0109b10f-3593-40a1-9ae1-93a48ca4db7b)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchUserIdByVerificationTokenResponse projection = projection();

        Assertions.assertEquals(585087404, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchUserIdByVerificationTokenResponse projection1 = projection();
        FetchUserIdByVerificationTokenResponse projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchUserIdByVerificationTokenResponse projection = projection();

        Assertions.assertNotEquals(projection, new FetchUserIdByVerificationTokenResponse(UUID.randomUUID()));
    }

    private FetchUserIdByVerificationTokenResponse projection() {

        return new FetchUserIdByVerificationTokenResponse(UUID.fromString("0109b10f-3593-40a1-9ae1-93a48ca4db7b"));
    }
}
