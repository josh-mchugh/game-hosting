package com.example.demo.project.aggregate.saga.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectCreateInstanceDetailsQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchProjectCreateInstanceDetailsQuery model = new FetchProjectCreateInstanceDetailsQuery(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectCreateInstanceDetailsQuery model = model();

        String expected = "FetchProjectCreateInstanceDetailsQuery(id=24a971f1-afa2-48c7-8f97-c3a7481a7f99)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectCreateInstanceDetailsQuery model = model();

        Assertions.assertEquals(1283884355, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectCreateInstanceDetailsQuery model1 = model();
        FetchProjectCreateInstanceDetailsQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectCreateInstanceDetailsQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectCreateInstanceDetailsQuery(null));
    }

    private FetchProjectCreateInstanceDetailsQuery model() {

        return new FetchProjectCreateInstanceDetailsQuery(UUID.fromString("24a971f1-afa2-48c7-8f97-c3a7481a7f99"));
    }
}
