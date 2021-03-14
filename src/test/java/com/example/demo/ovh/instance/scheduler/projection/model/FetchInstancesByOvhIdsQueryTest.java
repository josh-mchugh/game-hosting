package com.example.demo.ovh.instance.scheduler.projection.model;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchInstancesByOvhIdsQueryTest {

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        FetchInstancesByOvhIdsQuery model = new FetchInstancesByOvhIdsQuery(ImmutableList.of("ovhId"));

        Assertions.assertEquals(ImmutableList.of("ovhId"), model.getOvhIds());
    }

    @Test
    public void whenModelToString() {

        FetchInstancesByOvhIdsQuery model = model();

        String expected = "FetchInstancesByOvhIdsQuery(ovhIds=[ovhId])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchInstancesByOvhIdsQuery model = model();

        Assertions.assertEquals(106128566, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchInstancesByOvhIdsQuery model1 = model();
        FetchInstancesByOvhIdsQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchInstancesByOvhIdsQuery model = model();

        Assertions.assertNotEquals(model, new FetchInstancesByOvhIdsQuery(null));
    }

    private FetchInstancesByOvhIdsQuery model() {

        return new FetchInstancesByOvhIdsQuery(ImmutableList.of("ovhId"));
    }
}
