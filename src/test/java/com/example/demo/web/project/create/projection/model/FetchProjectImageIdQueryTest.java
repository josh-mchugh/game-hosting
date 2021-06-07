package com.example.demo.web.project.create.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectImageIdQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchProjectImageIdQuery model = new FetchProjectImageIdQuery(id, null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        FetchProjectImageIdQuery model = new FetchProjectImageIdQuery(null, "flavorId");

        Assertions.assertEquals("flavorId", model.getFlavorId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectImageIdQuery model = model();

        String expected = "FetchProjectImageIdQuery(id=24a971f1-afa2-48c7-8f97-c3a7481a7f99, flavorId=flavorId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectImageIdQuery model = model();

        Assertions.assertEquals(68332234, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectImageIdQuery model1 = model();
        FetchProjectImageIdQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectImageIdQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectImageIdQuery(null, null));
    }

    private FetchProjectImageIdQuery model() {

        return new FetchProjectImageIdQuery(UUID.fromString("24a971f1-afa2-48c7-8f97-c3a7481a7f99"), "flavorId");
    }
}
