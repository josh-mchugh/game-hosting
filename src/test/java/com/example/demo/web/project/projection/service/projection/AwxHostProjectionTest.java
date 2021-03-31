package com.example.demo.web.project.projection.service.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostProjection model = new AwxHostProjection(id.toString(), 1L);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxHostProjection model = new AwxHostProjection(UUID.randomUUID().toString(), 1L);

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelToString() {

        AwxHostProjection model = model();

        String expected = "AwxHostProjection(id=9dc3f8d2-165f-42cb-8b7b-9af22827a99b, awxId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxHostProjection model = model();

        Assertions.assertEquals(1683995242, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxHostProjection model1 = model();
        AwxHostProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxHostProjection model = model();

        Assertions.assertNotEquals(model, new AwxHostProjection(UUID.randomUUID().toString(), null));
    }

    private AwxHostProjection model() {

        return new AwxHostProjection(UUID.fromString("9dc3f8d2-165f-42cb-8b7b-9af22827a99b").toString(), 1L);
    }
}
