package com.example.demo.project.aggregate.saga.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectRegionNameByIdQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchProjectRegionNameByIdQuery model = new FetchProjectRegionNameByIdQuery(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectRegionNameByIdQuery model = model();

        String expected = "FetchProjectRegionNameByIdQuery(id=0e107c2c-ba77-4d57-a78c-cb5b18bde58b)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectRegionNameByIdQuery model = model();

        Assertions.assertEquals(190193638, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectRegionNameByIdQuery model1 = model();
        FetchProjectRegionNameByIdQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectRegionNameByIdQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectRegionNameByIdQuery(null));
    }

    private FetchProjectRegionNameByIdQuery model() {

        return new FetchProjectRegionNameByIdQuery(UUID.fromString("0e107c2c-ba77-4d57-a78c-cb5b18bde58b"));
    }
}
