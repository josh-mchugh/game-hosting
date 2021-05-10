package com.example.demo.web.project.create.projection.model;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectAvailableServersMapResponseTest {

    @Test
    public void whenModelHasAvailableServersThenReturnAvailableServers() {

        FetchProjectAvailableServersMapResponse model = new FetchProjectAvailableServersMapResponse(ImmutableMap.of());

        Assertions.assertEquals(ImmutableMap.of(), model.getAvailableServers());
    }

    @Test
    public void whenModelToString() {

        FetchProjectAvailableServersMapResponse model = model();

        String expected = "FetchProjectAvailableServersMapResponse(availableServers={})";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectAvailableServersMapResponse model = model();

        Assertions.assertEquals(59, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectAvailableServersMapResponse model1 = model();
        FetchProjectAvailableServersMapResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectAvailableServersMapResponse model = model();

        Assertions.assertNotEquals(model, new FetchProjectAvailableServersMapResponse(null));
    }

    private FetchProjectAvailableServersMapResponse model() {

        return new FetchProjectAvailableServersMapResponse(ImmutableMap.of());
    }
}
