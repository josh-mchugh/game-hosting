package com.example.demo.web.project.dashboard.projection.projection;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectDetailProjectionTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        ProjectDetailsProjection model = new ProjectDetailsProjection("name", null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        ProjectDetailsProjection model = new ProjectDetailsProjection(null, GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelToString() {

        ProjectDetailsProjection model = model();

        String expected = "ProjectDetailsProjection(name=name, gameType=MINECRAFT_JAVA)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectDetailsProjection model = new ProjectDetailsProjection("name", null);

        Assertions.assertEquals(199052237, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectDetailsProjection model1 = model();
        ProjectDetailsProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectDetailsProjection model = model();

        Assertions.assertNotEquals(model, new ProjectDetailsProjection("diffName", GameType.MINECRAFT_BEDROCK));
    }

    private ProjectDetailsProjection model() {

        return new ProjectDetailsProjection("name", GameType.MINECRAFT_JAVA);
    }
}
