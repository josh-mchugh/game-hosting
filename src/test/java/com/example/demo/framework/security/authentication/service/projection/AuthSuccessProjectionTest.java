package com.example.demo.framework.security.authentication.service.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AuthSuccessProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AuthSuccessProjection model = new AuthSuccessProjection(id.toString(), false);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasIsAdminThenExpectIsAdmin() {

        AuthSuccessProjection model = new AuthSuccessProjection(UUID.randomUUID().toString(), true);

        Assertions.assertTrue(model.isAdmin());
    }

    @Test
    public void whenModelToString() {

        AuthSuccessProjection model = model();

        String expected = "AuthSuccessProjection(id=892bfa69-51ff-470d-a9aa-1a0ef5ce2f4c, isAdmin=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AuthSuccessProjection model = model();

        Assertions.assertEquals(-2068797452, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AuthSuccessProjection model1 = model();
        AuthSuccessProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AuthSuccessProjection model = model();

        Assertions.assertNotEquals(model, new AuthSuccessProjection(UUID.randomUUID().toString(), false));
    }

    private AuthSuccessProjection model() {

        return new AuthSuccessProjection(UUID.fromString("892bfa69-51ff-470d-a9aa-1a0ef5ce2f4c").toString(), true);
    }
}
