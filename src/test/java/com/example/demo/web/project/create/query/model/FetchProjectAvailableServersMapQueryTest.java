package com.example.demo.web.project.create.query.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectAvailableServersMapQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchProjectAvailableServersMapQuery model = new FetchProjectAvailableServersMapQuery(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectAvailableServersMapQuery model = model();

        String expected = "FetchProjectAvailableServersMapQuery(id=c64093ff-cb77-43e4-a5f6-b74a1e64ca6b)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectAvailableServersMapQuery model = model();

        Assertions.assertEquals(-1230656139, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectAvailableServersMapQuery model1 = model();
        FetchProjectAvailableServersMapQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectAvailableServersMapQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectAvailableServersMapQuery(null));
    }

    private FetchProjectAvailableServersMapQuery model() {

        return new FetchProjectAvailableServersMapQuery(UUID.fromString("c64093ff-cb77-43e4-a5f6-b74a1e64ca6b"));
    }
}
