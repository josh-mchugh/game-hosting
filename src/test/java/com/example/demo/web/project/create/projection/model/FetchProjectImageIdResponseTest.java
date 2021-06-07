package com.example.demo.web.project.create.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectImageIdResponseTest {

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        UUID id = UUID.randomUUID();

        FetchProjectImageIdResponse model = new FetchProjectImageIdResponse(id);

        Assertions.assertEquals(id, model.getImageId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectImageIdResponse model = model();

        String expected = "FetchProjectImageIdResponse(imageId=fe9d6082-b30a-4b3c-968b-3434b9e0c59c)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectImageIdResponse model = model();

        Assertions.assertEquals(1660738129, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectImageIdResponse model1 = model();
        FetchProjectImageIdResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectImageIdResponse model = model();

        Assertions.assertNotEquals(model, new FetchProjectImageIdResponse(null));
    }

    private FetchProjectImageIdResponse model() {

        return new FetchProjectImageIdResponse(UUID.fromString("fe9d6082-b30a-4b3c-968b-3434b9e0c59c"));
    }
}
