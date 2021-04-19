package com.example.demo.framework.seed.awx.project.projection.model;

import com.example.demo.framework.seed.awx.project.projection.projection.AwxCredentialProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchAwxCredentialIdByNameResponseTest {

    @Test
    public void whenModelHasProjectThenReturnProject() {

        AwxCredentialProjection projection = new AwxCredentialProjection(UUID.fromString("87c0fb2a-8fe6-44af-914e-eb15291d8f20").toString(), 1L);
        FetchAwxCredentialByNameResponse model = new FetchAwxCredentialByNameResponse(projection);

        AwxCredentialProjection expected = new AwxCredentialProjection(UUID.fromString("87c0fb2a-8fe6-44af-914e-eb15291d8f20").toString(), 1L);

        Assertions.assertEquals(expected, model.getProjection());
    }

    @Test
    public void whenModelToString() {

        FetchAwxCredentialByNameResponse model = model();

        String expected = "FetchAwxCredentialByNameResponse(projection=AwxCredentialProjection(id=87c0fb2a-8fe6-44af-914e-eb15291d8f20, awxId=1))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAwxCredentialByNameResponse model = model();

        Assertions.assertEquals(-1334449729, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAwxCredentialByNameResponse model1 = model();
        FetchAwxCredentialByNameResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAwxCredentialByNameResponse model = model();

        Assertions.assertNotEquals(model, new FetchAwxCredentialByNameResponse(null));
    }

    private FetchAwxCredentialByNameResponse model() {

        return new FetchAwxCredentialByNameResponse(new AwxCredentialProjection(UUID.fromString("87c0fb2a-8fe6-44af-914e-eb15291d8f20").toString(), 1L));
    }
}
