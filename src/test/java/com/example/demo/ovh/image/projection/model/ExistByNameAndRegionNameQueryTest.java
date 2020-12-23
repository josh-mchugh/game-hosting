package com.example.demo.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistByNameAndRegionNameQueryTest {

    @Test
    public void whenQueryHasNameThenReturnName() {

        ExistByNameAndRegionNameQuery query = ExistByNameAndRegionNameQuery.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", query.getName());
    }

    @Test
    public void whenQueryHasRegionNameThenReturnRegionName() {

        ExistByNameAndRegionNameQuery query = ExistByNameAndRegionNameQuery.builder()
                .regionName("region-name")
                .build();

        Assertions.assertEquals("region-name", query.getRegionName());
    }

    @Test
    public void whenQueryToString() {

        ExistByNameAndRegionNameQuery query = query();

        String toString = "ExistByNameAndRegionNameQuery(name=name, regionName=region-name)";

        Assertions.assertEquals(toString, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        ExistByNameAndRegionNameQuery query = query();

        Assertions.assertEquals(-1412892314, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        ExistByNameAndRegionNameQuery query1 = query();
        ExistByNameAndRegionNameQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        ExistByNameAndRegionNameQuery query = query();

        Assertions.assertNotEquals(query, new ExistByNameAndRegionNameQuery("",""));
    }

    private ExistByNameAndRegionNameQuery query() {

        return new ExistByNameAndRegionNameQuery("name", "region-name");
    }
}
