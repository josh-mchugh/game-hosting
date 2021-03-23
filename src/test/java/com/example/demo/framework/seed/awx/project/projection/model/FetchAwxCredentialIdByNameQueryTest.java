package com.example.demo.framework.seed.awx.project.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAwxCredentialIdByNameQueryTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        FetchAwxCredentialByNameQuery model = new FetchAwxCredentialByNameQuery("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelToString() {

        FetchAwxCredentialByNameQuery model = model();

        String expected = "FetchAwxCredentialByNameQuery(name=name)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAwxCredentialByNameQuery model = model();

        Assertions.assertEquals(3373766, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAwxCredentialByNameQuery model1 = model();
        FetchAwxCredentialByNameQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAwxCredentialByNameQuery model = model();

        Assertions.assertNotEquals(model, new FetchAwxCredentialByNameQuery(null));
    }

    private FetchAwxCredentialByNameQuery model() {

        return new FetchAwxCredentialByNameQuery("name");
    }
}
