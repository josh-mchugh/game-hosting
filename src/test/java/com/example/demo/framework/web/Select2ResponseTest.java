package com.example.demo.framework.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Select2ResponseTest {

    @Test
    public void whenModelHasResultsThenReturnResults() {

        Select2Response<Object> results = new Select2Response<>(new ArrayList<>());

        Assertions.assertEquals(new ArrayList<>(), results.getResults());
    }

    @Test
    public void whenModelToString() {

        Select2Response<Object> model = model();

        String expected = "Select2Response(results=[])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        Select2Response<Object> model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        Select2Response<Object> model1 = model();
        Select2Response<Object> model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        Select2Response<Object> model = model();

        Assertions.assertNotEquals(model, new Select2Response<>(null));
    }

    private Select2Response<Object> model() {

        return new Select2Response<>(new ArrayList<>());
    }
}
