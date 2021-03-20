package com.example.demo.framework.seed.awx.credential.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyAwxCredentialQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyAwxCredentialQuery model = model();

        String expected = "ExistsAnyAwxCredentialQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyAwxCredentialQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyAwxCredentialQuery model1 = model();
        ExistsAnyAwxCredentialQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyAwxCredentialQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyAwxCredentialQuery()));
    }

    private ExistsAnyAwxCredentialQuery model() {

        return new ExistsAnyAwxCredentialQuery();
    }
}
