package com.example.demo.web.admin.game.service.projection;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminGameServerTableProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection("id", null, null, null, null, null, null);

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection(null, "name", null, null, null, null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection(null, null, GameType.MINECRAFT_JAVA, null, null, null, null);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection(null, null, null, "regionName", null, null, null);

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelHasVcpusThenReturnVcpus() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection(null, null, null, null, 1, null, null);

        Assertions.assertEquals(1, model.getVcpus());
    }

    @Test
    public void whenModelHasRamThenReturnRam() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection(null, null, null, null, null, 1, null);

        Assertions.assertEquals(1, model.getRam());
    }

    @Test
    public void whenModelHasImageNameThenReturnImageName() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection(null, null, null, null, null, null, "imageName");

        Assertions.assertEquals("imageName", model.getImageName());
    }

    @Test
    public void whenModelToString() {

        AdminGameServerTableProjection model = model();

        String expected = "AdminGameServerTableProjection(id=id, name=name, gameType=MINECRAFT_JAVA, regionName=regionName, vcpus=1, ram=2, imageName=imageName)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminGameServerTableProjection model = new AdminGameServerTableProjection("id", "name", null, "regionName", 1, 2, "imageName");;

        Assertions.assertEquals(-735748260, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminGameServerTableProjection model1 = model();
        AdminGameServerTableProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AdminGameServerTableProjection model = model();

        Assertions.assertNotEquals(model, new AdminGameServerTableProjection(null, null, null, null, null, null, null));
    }

    private AdminGameServerTableProjection model() {

        return new AdminGameServerTableProjection("id", "name", GameType.MINECRAFT_JAVA, "regionName", 1, 2, "imageName");
    }
}
