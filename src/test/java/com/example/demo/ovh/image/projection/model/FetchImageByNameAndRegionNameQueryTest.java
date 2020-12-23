package com.example.demo.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchImageByNameAndRegionNameQueryTest {

    @Test
    public void whenQueryHasNameThenReturnName() {

        FetchImageByNameAndRegionNameQuery query = FetchImageByNameAndRegionNameQuery.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", query.getName());
    }

    @Test
    public void whenQueryHasRegionNameThenReturnRegionName() {

        FetchImageByNameAndRegionNameQuery query = FetchImageByNameAndRegionNameQuery.builder()
                .regionName("regionName")
                .build();

        Assertions.assertEquals("regionName", query.getRegionName());
    }

    @Test
    public void whenQueryToString() {

        FetchImageByNameAndRegionNameQuery query = query();

        String expected = "FetchImageByNameAndRegionNameQuery(name=name, regionName=regionName)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchImageByNameAndRegionNameQuery query = query();

        Assertions.assertEquals(-1791637695, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchImageByNameAndRegionNameQuery query1 = query();
        FetchImageByNameAndRegionNameQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchImageByNameAndRegionNameQuery query = query();

        Assertions.assertNotEquals(query, FetchImageByNameAndRegionNameQuery.builder().build());
    }

    private FetchImageByNameAndRegionNameQuery query() {

        return FetchImageByNameAndRegionNameQuery.builder()
                .name("name")
                .regionName("regionName")
                .build();
    }
}
