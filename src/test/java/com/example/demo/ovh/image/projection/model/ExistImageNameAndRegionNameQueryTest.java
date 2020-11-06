package com.example.demo.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistImageNameAndRegionNameQueryTest {

    @Test
    public void whenQueryHasNameThenReturnName() {

        ExistImageNameAndRegionNameQuery query = ExistImageNameAndRegionNameQuery.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", query.getName());
    }

    @Test
    public void whenQueryHasRegionNameThenReturnRegionName() {

        ExistImageNameAndRegionNameQuery query = ExistImageNameAndRegionNameQuery.builder()
                .regionName("region-name")
                .build();

        Assertions.assertEquals("region-name", query.getRegionName());
    }

    @Test
    public void whenQueryToString() {

        ExistImageNameAndRegionNameQuery query = query();

        String toString = "ExistImageNameAndRegionNameQuery(name=name, regionName=region-name)";

        Assertions.assertEquals(toString, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        ExistImageNameAndRegionNameQuery query = query();

        Assertions.assertEquals(-1412892314, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        ExistImageNameAndRegionNameQuery query1 = query();
        ExistImageNameAndRegionNameQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        ExistImageNameAndRegionNameQuery query = query();

        Assertions.assertNotEquals(query, new ExistImageNameAndRegionNameQuery("",""));
    }

    private ExistImageNameAndRegionNameQuery query() {

        return new ExistImageNameAndRegionNameQuery("name", "region-name");
    }
}
