package com.example.demo.project.aggregate.saga.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAnsibleCredentialQueryTest {

    @Test
    public void whenModelToString() {

        FetchAnsibleCredentialQuery model = model();

        String expected = "FetchAnsibleCredentialQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAnsibleCredentialQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAnsibleCredentialQuery model1 = model();
        FetchAnsibleCredentialQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAnsibleCredentialQuery model = model();

        Assertions.assertFalse(!model.equals(new FetchAnsibleCredentialQuery()));
    }

    private FetchAnsibleCredentialQuery model() {

        return new FetchAnsibleCredentialQuery();
    }
}
