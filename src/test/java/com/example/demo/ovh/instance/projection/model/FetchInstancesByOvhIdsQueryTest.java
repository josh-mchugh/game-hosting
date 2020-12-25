package com.example.demo.ovh.instance.projection.model;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchInstancesByOvhIdsQueryTest {

    @Test
    public void whenQueryHasOvhIdsThenReturnOvhIds() {

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(ImmutableList.of("id1", "id2"));

        Assertions.assertEquals(ImmutableList.of("id1", "id2"), query.getOvhIds());
    }

    @Test
    public void whenQueryToString() {

        FetchInstancesByOvhIdsQuery query = query();

        String expected = "FetchInstancesByOvhIdsQuery(ovhIds=[id1, id2])";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchInstancesByOvhIdsQuery query = query();

        Assertions.assertEquals(3330749, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchInstancesByOvhIdsQuery query1 = query();
        FetchInstancesByOvhIdsQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchInstancesByOvhIdsQuery query = query();

        Assertions.assertNotEquals(query, new FetchInstancesByOvhIdsQuery(ImmutableList.of()));
    }

    private FetchInstancesByOvhIdsQuery query() {

        return new FetchInstancesByOvhIdsQuery(ImmutableList.of("id1", "id2"));
    }
}
