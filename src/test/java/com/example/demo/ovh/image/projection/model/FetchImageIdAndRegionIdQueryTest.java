package com.example.demo.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchImageIdAndRegionIdQueryTest {

    @Test
    public void whenQueryHasImageNameThenReturnImageName() {

        FetchImageIdAndRegionIdQuery query = FetchImageIdAndRegionIdQuery.builder()
                .imageName("image-name")
                .build();

        Assertions.assertEquals("image-name", query.getImageName());
    }

    @Test
    public void whenQueryHasRegionNameThenReturnRegionName() {

        FetchImageIdAndRegionIdQuery query = FetchImageIdAndRegionIdQuery.builder()
                .regionName("region-name")
                .build();

        Assertions.assertEquals("region-name", query.getRegionName());
    }

    @Test
    public void whenQueryToString() {

        FetchImageIdAndRegionIdQuery query = query();

        String toString = "FetchImageIdAndRegionIdQuery(imageName=image-name, regionName=region-name)";

        Assertions.assertEquals(toString, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchImageIdAndRegionIdQuery query = query();

        Assertions.assertEquals(723306476, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchImageIdAndRegionIdQuery query1 = query();
        FetchImageIdAndRegionIdQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchImageIdAndRegionIdQuery query = query();

        Assertions.assertNotEquals(query, FetchImageIdAndRegionIdQuery.builder().build());
    }

    private FetchImageIdAndRegionIdQuery query() {

        return new FetchImageIdAndRegionIdQuery("image-name", "region-name");
    }
}
