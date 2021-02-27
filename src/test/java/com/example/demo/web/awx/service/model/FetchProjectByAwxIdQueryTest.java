package com.example.demo.web.awx.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectByAwxIdQueryTest {

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        FetchProjectByAwxIdQuery model = new FetchProjectByAwxIdQuery(1L);

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectByAwxIdQuery model = model();

        String expected = "FetchProjectByAwxIdQuery(awxId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectByAwxIdQuery model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectByAwxIdQuery model1 = model();
        FetchProjectByAwxIdQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectByAwxIdQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectByAwxIdQuery(2L));
    }

    private FetchProjectByAwxIdQuery model() {

        return new FetchProjectByAwxIdQuery(1L);
    }
}
