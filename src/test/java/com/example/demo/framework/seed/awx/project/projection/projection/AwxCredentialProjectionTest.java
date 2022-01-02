package com.example.demo.framework.seed.awx.project.projection.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxCredentialProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AwxCredentialProjection model = new AwxCredentialProjection("96e0f262-597a-4503-adf3-021b7425d5c7", 1L);

        Assertions.assertEquals("96e0f262-597a-4503-adf3-021b7425d5c7", model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxCredentialProjection model = new AwxCredentialProjection(UUID.randomUUID().toString(), 1L);

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelToString() {

        AwxCredentialProjection model = model();

        String expected = "AwxCredentialProjection(id=fc8ff45e-a418-4e2e-8c12-4348297b9e87, awxId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxCredentialProjection model = model();

        Assertions.assertEquals(-2036249605, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxCredentialProjection model1 = model();
        AwxCredentialProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxCredentialProjection model = model();

        Assertions.assertNotEquals(model, new AwxCredentialProjection(UUID.randomUUID().toString(), null));
    }

    private AwxCredentialProjection model() {

        return new AwxCredentialProjection(UUID.fromString("fc8ff45e-a418-4e2e-8c12-4348297b9e87").toString(), 1L);
    }
}
