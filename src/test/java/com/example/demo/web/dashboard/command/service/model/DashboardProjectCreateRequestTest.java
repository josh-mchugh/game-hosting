package com.example.demo.web.dashboard.command.service.model;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DashboardProjectCreateRequestTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        DashboardProjectCreateRequest model = DashboardProjectCreateRequest.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        DashboardProjectCreateRequest model = DashboardProjectCreateRequest.builder()
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelHasRegionThenReturnRegion() {

        DashboardProjectCreateRequest model = DashboardProjectCreateRequest.builder()
                .region("region")
                .build();

        Assertions.assertEquals("region", model.getRegion());
    }

    @Test
    public void whenModelHasFlavorThenReturnFlavor() {

        DashboardProjectCreateRequest model = DashboardProjectCreateRequest.builder()
                .flavor("flavor")
                .build();

        Assertions.assertEquals("flavor", model.getFlavor());
    }

    @Test
    public void whenModelToString() {

        DashboardProjectCreateRequest model = model();

        String expected = "DashboardProjectCreateRequest(name=name, gameType=MINECRAFT_JAVA, region=region, flavor=flavor)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        DashboardProjectCreateRequest model = DashboardProjectCreateRequest.builder()
                .name("name")
                .region("region")
                .flavor("flavor")
                .build();

        Assertions.assertEquals(820693855, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        DashboardProjectCreateRequest model1 = model();
        DashboardProjectCreateRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        DashboardProjectCreateRequest model = model();

        Assertions.assertNotEquals(model, DashboardProjectCreateRequest.builder().build());
    }

    private DashboardProjectCreateRequest model() {

        return DashboardProjectCreateRequest.builder()
                .name("name")
                .gameType(GameType.MINECRAFT_JAVA)
                .region("region")
                .flavor("flavor")
                .build();
    }
}
