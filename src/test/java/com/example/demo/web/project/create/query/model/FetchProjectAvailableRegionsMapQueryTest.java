package com.example.demo.web.project.create.query.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectAvailableRegionsMapQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchProjectAvailableRegionsMapQuery model = new FetchProjectAvailableRegionsMapQuery(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectAvailableRegionsMapQuery model = model();

        String expected = "FetchProjectAvailableRegionsMapQuery(id=c64093ff-cb77-43e4-a5f6-b74a1e64ca6b)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectAvailableRegionsMapQuery model = model();

        Assertions.assertEquals(-1230656139, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectAvailableRegionsMapQuery model1 = model();
        FetchProjectAvailableRegionsMapQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectAvailableRegionsMapQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectAvailableRegionsMapQuery(null));
    }

    private FetchProjectAvailableRegionsMapQuery model() {

        return new FetchProjectAvailableRegionsMapQuery(UUID.fromString("c64093ff-cb77-43e4-a5f6-b74a1e64ca6b"));
    }
}
