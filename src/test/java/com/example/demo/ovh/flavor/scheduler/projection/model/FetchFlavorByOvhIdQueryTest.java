package com.example.demo.ovh.flavor.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchFlavorByOvhIdQueryTest {

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        FetchFlavorByOvhIdQuery model = new FetchFlavorByOvhIdQuery("ovhId");

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelToString() {

        FetchFlavorByOvhIdQuery model = model();

        String expected = "FetchFlavorByOvhIdQuery(ovhId=ovhId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchFlavorByOvhIdQuery model = model();

        Assertions.assertEquals(106128535, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchFlavorByOvhIdQuery model1 = model();
        FetchFlavorByOvhIdQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchFlavorByOvhIdQuery model = model();

        Assertions.assertNotEquals(model, new FetchFlavorByOvhIdQuery(null));
    }

    private FetchFlavorByOvhIdQuery model() {

        return new FetchFlavorByOvhIdQuery("ovhId");
    }
}
