package com.example.demo.framework.seed.ovh.credential.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsAnyCredentialQueryTest {

    @Test
    public void whenModelToString() {

        ExistsAnyCredentialQuery model = model();

        String expected = "ExistsAnyCredentialQuery()";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsAnyCredentialQuery model = model();

        Assertions.assertEquals(1, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsAnyCredentialQuery model1 = model();
        ExistsAnyCredentialQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsAnyCredentialQuery model = model();

        Assertions.assertFalse(!model.equals(new ExistsAnyCredentialQuery()));
    }

    private ExistsAnyCredentialQuery model() {

        return new ExistsAnyCredentialQuery();
    }
}
