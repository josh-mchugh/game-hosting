package com.example.demo.ovh.instance.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchInstanceDetailsByProjectIdQueryTest {

    @Test
    public void whenModelHasProjectIdThenReturnProjectId() {

        FetchInstanceDetailsByProjectIdQuery query = new FetchInstanceDetailsByProjectIdQuery("projectId");

        Assertions.assertEquals("projectId", query.getProjectId());
    }

    @Test
    public void whenModelToString() {

        FetchInstanceDetailsByProjectIdQuery query = query();

        String expected = "FetchInstanceDetailsByProjectIdQuery(projectId=projectId)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchInstanceDetailsByProjectIdQuery query = query();

        Assertions.assertEquals(-894832049, query.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchInstanceDetailsByProjectIdQuery query1 = query();
        FetchInstanceDetailsByProjectIdQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchInstanceDetailsByProjectIdQuery query = query();

        Assertions.assertNotEquals(query, new FetchInstanceDetailsByProjectIdQuery("diffProjectId"));
    }

    private FetchInstanceDetailsByProjectIdQuery query() {

        return new FetchInstanceDetailsByProjectIdQuery("projectId");
    }
}
