package com.example.demo.web.admin.game.service.projection;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminGameServerRegionProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AdminGameServerRegionProjection model = new AdminGameServerRegionProjection("id", null, null, null);

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AdminGameServerRegionProjection model = new AdminGameServerRegionProjection(null, "name", null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDataCenterThenDataCenter() {

        AdminGameServerRegionProjection model = new AdminGameServerRegionProjection(null, null, "datacenter", null);

        Assertions.assertEquals("datacenter", model.getDataCenter());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        AdminGameServerRegionProjection model = new AdminGameServerRegionProjection(null, null, null, RegionStatus.UP);

        Assertions.assertEquals(RegionStatus.UP, model.getStatus());
    }

    @Test
    public void whenModelToString() {

        AdminGameServerRegionProjection model = model();

        String expected = "AdminGameServerRegionProjection(id=id, name=name, dataCenter=datacenter, status=UP)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminGameServerRegionProjection model = new AdminGameServerRegionProjection("id", "name", "datacenter", null);

        Assertions.assertEquals(-1974456571, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminGameServerRegionProjection model1 = model();
        AdminGameServerRegionProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AdminGameServerRegionProjection model = model();

        Assertions.assertNotEquals(model, new AdminGameServerRegionProjection(null, null, null, null));
    }

    private AdminGameServerRegionProjection model() {

        return new AdminGameServerRegionProjection("id", "name", "datacenter", RegionStatus.UP);
    }
}
