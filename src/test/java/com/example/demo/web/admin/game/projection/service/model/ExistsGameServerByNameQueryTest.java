package com.example.demo.web.admin.game.projection.service.model;

import com.example.demo.web.admin.game.projection.service.model.ExistsGameServerByNameQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsGameServerByNameQueryTest {

    @Test
    public void whenQueryHasNameThenReturnName() {

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery("name");

        Assertions.assertEquals("name", query.getName());
    }

    @Test
    public void whenQueryToString() {

        ExistsGameServerByNameQuery query = query();

        String expected = "ExistsGameServerByNameQuery(name=name)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        ExistsGameServerByNameQuery query = query();

        Assertions.assertEquals(3373766, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        ExistsGameServerByNameQuery query1 = query();
        ExistsGameServerByNameQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        ExistsGameServerByNameQuery query = query();

        Assertions.assertNotEquals(query, new ExistsGameServerByNameQuery(""));
    }

    private ExistsGameServerByNameQuery query() {

        return new ExistsGameServerByNameQuery("name");
    }
}
