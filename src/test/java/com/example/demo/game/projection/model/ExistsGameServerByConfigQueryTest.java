package com.example.demo.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsGameServerByConfigQueryTest {

    @Test
    public void whenQueryHasGameIdThenReturnGameId() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId("gameId")
                .build();

        Assertions.assertEquals("gameId", query.getGameId());
    }

    @Test
    public void whenQueryHasRegionIdThenReturnRegionId() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .regionId("regionId")
                .build();

        Assertions.assertEquals("regionId", query.getRegionId());
    }

    @Test
    public void whenQueryHasFlavorIdThenReturnFlavorId() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .flavorId("flavorId")
                .build();

        Assertions.assertEquals("flavorId", query.getFlavorId());
    }

    @Test
    public void whenQueryHasImageIdThenReturnImageId() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .imageId("imageId")
                .build();

        Assertions.assertEquals("imageId", query.getImageId());
    }

    @Test
    public void whenQueryToString() {

        ExistsGameServerByConfigQuery query = query();

        String expected = "ExistsGameServerByConfigQuery(gameId=gameId, regionId=regionId, flavorId=flavorId, imageId=imageId)";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        ExistsGameServerByConfigQuery query = query();

        Assertions.assertEquals(1664814888, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        ExistsGameServerByConfigQuery query1 = query();
        ExistsGameServerByConfigQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        ExistsGameServerByConfigQuery query = query();

        Assertions.assertNotEquals(query, ExistsGameServerByConfigQuery.builder().build());
    }

    private ExistsGameServerByConfigQuery query() {

        return ExistsGameServerByConfigQuery.builder()
                .gameId("gameId")
                .regionId("regionId")
                .flavorId("flavorId")
                .imageId("imageId")
                .build();
    }
}
