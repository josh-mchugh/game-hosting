package com.example.demo.awx.feign;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TemplateResponseTest {

    @Test
    public void whenModelHasCountReturnCount() {

        ListResponse<Object> model = new ListResponse<>();
        model.setCount(1L);

        Assertions.assertEquals(1L, model.getCount());
    }

    @Test
    public void whenModelHasDefaultCountThenExpectZero() {

        Assertions.assertEquals(0, new ListResponse<>().getCount());
    }

    @Test
    public void whenModelHasNextThenReturnNext() {

        ListResponse<Object> model = new ListResponse<>();
        model.setNext(true);

        Assertions.assertTrue(model.getNext());
    }

    @Test
    public void whenModelHasDefaultNextThenExpectFalse() {

        Assertions.assertFalse(new ListResponse<>().getNext());
    }

    @Test
    public void whenModelHasPreviousThenReturnPrevious() {

        ListResponse<Object> model = new ListResponse<>();
        model.setPrevious(true);

        Assertions.assertTrue(model.getPrevious());
    }

    @Test
    public void whenModelHasDefaultPreviousThenExpectFalse() {

        Assertions.assertFalse(new ListResponse<>().getPrevious());
    }

    @Test
    public void whenModelHasResultsThenExpectResults() {

        List<Object> expected = Arrays.asList(new Object(), new Object());

        ListResponse<Object> result = new ListResponse<>();
        result.setResults(expected);

        Assertions.assertEquals(expected, result.getResults());
    }

    @Test
    public void whenModelHasDefaultResultsThenExpectEmptyList() {

        Assertions.assertTrue(CollectionUtils.isEmpty(new ListResponse<>().getResults()));
    }

    @Test
    public void whenModelToString() {

        ListResponse<String> model = model();

        String expected = "ListResponse(count=20, next=true, previous=true, results=[test1, test2])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ListResponse<String> model = model();

        Assertions.assertEquals(-746336069, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ListResponse<String> model1 = model();
        ListResponse<String> model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ListResponse<String> model = model();

        Assertions.assertNotEquals(model, new ListResponse<>());
    }

    public ListResponse<String> model() {

        ListResponse<String> response = new ListResponse<>();
        response.setCount(20L);
        response.setNext(true);
        response.setPrevious(true);
        response.setResults(Arrays.asList("test1", "test2"));

        return response;
    }
}
