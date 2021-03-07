package com.example.demo.ovh.flavor.scheduler.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsFlavorByOvhIdQueryTest {

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        ExistsFlavorByOvhIdQuery model = new ExistsFlavorByOvhIdQuery("ovhId");

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelToString() {

        ExistsFlavorByOvhIdQuery model = model();

        String expected = "ExistsFlavorByOvhIdQuery(ovhId=ovhId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsFlavorByOvhIdQuery model = model();

        Assertions.assertEquals(106128535, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsFlavorByOvhIdQuery model1 = model();
        ExistsFlavorByOvhIdQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsFlavorByOvhIdQuery model = model();

        Assertions.assertNotEquals(model, new ExistsFlavorByOvhIdQuery(null));
    }

    private ExistsFlavorByOvhIdQuery model() {

        return new ExistsFlavorByOvhIdQuery("ovhId");
    }
}
