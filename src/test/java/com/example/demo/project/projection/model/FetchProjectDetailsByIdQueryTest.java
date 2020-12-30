package com.example.demo.project.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectDetailsByIdQueryTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        FetchProjectDetailsByIdQuery query = new FetchProjectDetailsByIdQuery("id");

        Assertions.assertEquals("id", query.getId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectDetailsByIdQuery query = query();

        String expected = "FetchProjectDetailsByIdQuery(id=id)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectDetailsByIdQuery query = query();

        Assertions.assertEquals(3414, query.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectDetailsByIdQuery query1 = query();
        FetchProjectDetailsByIdQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectDetailsByIdQuery query = query();

        Assertions.assertNotEquals(query, new FetchProjectDetailsByIdQuery("diffId"));
    }

    private FetchProjectDetailsByIdQuery query() {

        return new FetchProjectDetailsByIdQuery("id");
    }
}
