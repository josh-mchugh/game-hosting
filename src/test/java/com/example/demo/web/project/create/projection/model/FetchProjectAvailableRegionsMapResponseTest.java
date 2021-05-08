package com.example.demo.web.project.create.projection.model;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectAvailableRegionsMapResponseTest {

    @Test
    public void whenModelHasAvailableRegionsThenReturnAvailableRegions() {

        FetchProjectAvailableRegionsMapResponse model = new FetchProjectAvailableRegionsMapResponse(ImmutableMap.of());

        Assertions.assertEquals(ImmutableMap.of(), model.getAvailableRegions());
    }

    @Test
    public void whenModelToString() {

        FetchProjectAvailableRegionsMapResponse model = model();

        String expected = "FetchProjectAvailableRegionsMapResponse(availableRegions={})";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectAvailableRegionsMapResponse model = model();

        Assertions.assertEquals(59, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectAvailableRegionsMapResponse model1 = model();
        FetchProjectAvailableRegionsMapResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectAvailableRegionsMapResponse model = model();

        Assertions.assertNotEquals(model, new FetchProjectAvailableRegionsMapResponse(null));
    }

    private FetchProjectAvailableRegionsMapResponse model() {

        return new FetchProjectAvailableRegionsMapResponse(ImmutableMap.of());
    }
}
