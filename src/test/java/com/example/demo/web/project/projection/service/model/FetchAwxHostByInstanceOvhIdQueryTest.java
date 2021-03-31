package com.example.demo.web.project.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAwxHostByInstanceOvhIdQueryTest {

   @Test
   public void whenModelHasInstanceOvhIdThenReturnInstanceOvhId() {

       FetchAwxHostByInstanceOvhIdQuery model = new FetchAwxHostByInstanceOvhIdQuery("instanceOvhId");

       Assertions.assertEquals("instanceOvhId", model.getInstanceOvhId());
   }

    @Test
    public void whenModelToString() {

        FetchAwxHostByInstanceOvhIdQuery request = model();

        String expected = "FetchAwxHostByInstanceOvhIdQuery(instanceOvhId=instanceOvhId)";

        Assertions.assertEquals(expected, request.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAwxHostByInstanceOvhIdQuery model = model();

        Assertions.assertEquals(-1397197566, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAwxHostByInstanceOvhIdQuery model1 = model();
        FetchAwxHostByInstanceOvhIdQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAwxHostByInstanceOvhIdQuery model = model();

        Assertions.assertNotEquals(model, new FetchAwxHostByInstanceOvhIdQuery(null));
    }

    private FetchAwxHostByInstanceOvhIdQuery model() {

        return new FetchAwxHostByInstanceOvhIdQuery("instanceOvhId");
    }
}
