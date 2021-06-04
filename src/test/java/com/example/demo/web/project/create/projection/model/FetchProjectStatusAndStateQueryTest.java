package com.example.demo.web.project.create.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectStatusAndStateQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchProjectStatusAndStateQuery model = new FetchProjectStatusAndStateQuery(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectStatusAndStateQuery model = model();

        String expected = "FetchProjectStatusAndStateQuery(id=c64093ff-cb77-43e4-a5f6-b74a1e64ca6b)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectStatusAndStateQuery model = model();

        Assertions.assertEquals(-1230656139, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectStatusAndStateQuery model1 = model();
        FetchProjectStatusAndStateQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectStatusAndStateQuery model = model();

        Assertions.assertNotEquals(model, new FetchProjectStatusAndStateQuery(null));
    }

    private FetchProjectStatusAndStateQuery model() {

        return new FetchProjectStatusAndStateQuery(UUID.fromString("c64093ff-cb77-43e4-a5f6-b74a1e64ca6b"));
    }
}
