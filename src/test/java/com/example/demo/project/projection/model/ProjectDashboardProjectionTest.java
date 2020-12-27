package com.example.demo.project.projection.model;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectDashboardProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        ProjectDashboardProjection model = new ProjectDashboardProjection("id", null, null);

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        ProjectDashboardProjection model = new ProjectDashboardProjection(null, "name", null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        ProjectDashboardProjection model = new ProjectDashboardProjection(null, null, GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelToString() {

        ProjectDashboardProjection model = model();

        String expected = "ProjectDashboardProjection(id=id, name=name, gameType=MINECRAFT_JAVA)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectDashboardProjection model = new ProjectDashboardProjection("id", "name", null);

        Assertions.assertEquals(210932890, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectDashboardProjection model1 = model();
        ProjectDashboardProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectDashboardProjection model = model();

        Assertions.assertNotEquals(model, new ProjectDashboardProjection(null, null, null));
    }

    private ProjectDashboardProjection model() {

        return new ProjectDashboardProjection("id", "name", GameType.MINECRAFT_JAVA);
    }
}
