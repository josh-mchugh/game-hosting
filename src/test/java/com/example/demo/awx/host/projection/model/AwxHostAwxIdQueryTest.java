package com.example.demo.awx.host.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxHostAwxIdQueryTest {

    @Test
    public void whenQueryHasInstanceIdThenReturnInstanceId() {

        AwxHostAwxIdQuery query = new AwxHostAwxIdQuery("instanceId");

        Assertions.assertEquals("instanceId", query.getInstanceId());
    }

    @Test
    public void whenQueryToString() {

        AwxHostAwxIdQuery query = query();

        String expected = "AwxHostAwxIdQuery(instanceId=instanceId)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        AwxHostAwxIdQuery query = query();

        Assertions.assertEquals(902024395, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        AwxHostAwxIdQuery query1 = query();
        AwxHostAwxIdQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        AwxHostAwxIdQuery query = query();

        Assertions.assertNotEquals(query, new AwxHostAwxIdQuery(null));
    }

    private AwxHostAwxIdQuery query() {

        return new AwxHostAwxIdQuery("instanceId");
    }
}
