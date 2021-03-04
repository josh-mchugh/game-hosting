package com.example.demo.framework.security.session.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchUserIdByEmailResponseTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchUserIdByEmailResponse model = new FetchUserIdByEmailResponse(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchUserIdByEmailResponse model = model();

        String expected = "FetchUserIdByEmailResponse(id=eb119501-a080-4ae1-92ee-0eb9cc369b6c)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserIdByEmailResponse model = model();

        Assertions.assertEquals(357124720, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserIdByEmailResponse model1 = model();
        FetchUserIdByEmailResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserIdByEmailResponse model = model();

        Assertions.assertNotEquals(model, new FetchUserIdByEmailResponse(null));
    }

    private FetchUserIdByEmailResponse model() {

        return new FetchUserIdByEmailResponse(UUID.fromString("eb119501-a080-4ae1-92ee-0eb9cc369b6c"));
    }
}
