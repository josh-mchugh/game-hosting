package com.example.demo.web.admin.ovh.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstanceGroupStatisticTest {

    @Test
    public void whenModelHasTotalThenReturnTotal() {

        InstanceGroupStatistic model = new InstanceGroupStatistic(1);

        Assertions.assertEquals(1, model.getTotal());
    }

    @Test
    public void whenModelToString() {

        InstanceGroupStatistic model = model();

        String expected = "InstanceGroupStatistic(total=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceGroupStatistic model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceGroupStatistic model1 = model();
        InstanceGroupStatistic model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceGroupStatistic model = model();

        Assertions.assertNotEquals(model, new InstanceGroupStatistic(2));
    }

    private InstanceGroupStatistic model() {

        return new InstanceGroupStatistic(1);
    }
}
