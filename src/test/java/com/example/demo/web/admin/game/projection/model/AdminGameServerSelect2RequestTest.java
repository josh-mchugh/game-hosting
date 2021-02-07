package com.example.demo.web.admin.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminGameServerSelect2RequestTest {

    @Test
    public void whenModelHasRegionIdThenReturnRegionId() {

        AdminGameServerSelect2Request model = new AdminGameServerSelect2Request();
        model.setRegionId("regionId");

        Assertions.assertEquals("regionId", model.getRegionId());
    }

    @Test
    public void whenModelHasSearchThenReturnSearch() {

        AdminGameServerSelect2Request model = new AdminGameServerSelect2Request();
        model.setSearch("search");

        Assertions.assertEquals("search", model.getSearch());
    }

    @Test
    public void whenModelToString() {

        AdminGameServerSelect2Request model = model();

        String expected = "AdminGameServerSelect2Request(regionId=regionId, search=search)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminGameServerSelect2Request model = model();

        Assertions.assertEquals(1313337110, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminGameServerSelect2Request model1= model();
        AdminGameServerSelect2Request model2= model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AdminGameServerSelect2Request model = model();

        Assertions.assertNotEquals(model, new AdminGameServerSelect2Request());
    }

    private AdminGameServerSelect2Request model() {

        AdminGameServerSelect2Request model = new AdminGameServerSelect2Request();
        model.setRegionId("regionId");
        model.setSearch("search");

        return model;
    }
}
