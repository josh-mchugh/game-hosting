package com.example.demo.web.project.dashboard.projection.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxHostProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AwxHostProjection model = new AwxHostProjection("id", 1L);

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxHostProjection model = new AwxHostProjection("id", 1L);

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelToString() {

        AwxHostProjection model = model();

        String expected = "AwxHostProjection(id=id, awxId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxHostProjection model = model();

        Assertions.assertEquals(6895, model.hashCode());
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

        Assertions.assertNotEquals(model, new AwxHostProjection("", null));
    }

    private AwxHostProjection model() {

        return new AwxHostProjection("id", 1L);
    }
}
