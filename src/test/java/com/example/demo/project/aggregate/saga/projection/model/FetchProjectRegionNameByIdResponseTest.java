package com.example.demo.project.aggregate.saga.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectRegionNameByIdResponseTest {

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        FetchProjectRegionNameByIdResponse model = new FetchProjectRegionNameByIdResponse("regionName");

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelToString() {

        FetchProjectRegionNameByIdResponse model = model();

        String expected = "FetchProjectRegionNameByIdResponse(regionName=regionName)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectRegionNameByIdResponse model = model();

        Assertions.assertEquals(-1990689830, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectRegionNameByIdResponse model1 = model();
        FetchProjectRegionNameByIdResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectRegionNameByIdResponse model = model();

        Assertions.assertNotEquals(model, new FetchProjectRegionNameByIdResponse(null));
    }

    private FetchProjectRegionNameByIdResponse model() {

        return new FetchProjectRegionNameByIdResponse("regionName");
    }
}
