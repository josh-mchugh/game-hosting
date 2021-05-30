package com.example.demo.web.project.dashboard.projection.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectDetailProjectionTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        ProjectDetailsProjection model = new ProjectDetailsProjection("name", null, null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        ProjectDetailsProjection model = new ProjectDetailsProjection(null, GameType.MINECRAFT_JAVA, null, null);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        ProjectDetailsProjection model = new ProjectDetailsProjection(null, null, ProjectStatus.ACTIVE, null);

        Assertions.assertEquals(ProjectStatus.ACTIVE, model.getStatus());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        ProjectDetailsProjection model = new ProjectDetailsProjection(null, null, null, ProjectState.ACTIVE);

        Assertions.assertEquals(ProjectState.ACTIVE, model.getState());
    }

    @Test
    public void whenModelToString() {

        ProjectDetailsProjection model = model();

        String expected = "ProjectDetailsProjection(name=name, gameType=MINECRAFT_JAVA, status=ACTIVE, state=ACTIVE)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectDetailsProjection model = new ProjectDetailsProjection("name", null, null, null);

        Assertions.assertEquals(1411104921, model.hashCode());
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

        Assertions.assertNotEquals(model, new ProjectDetailsProjection("diffName", GameType.MINECRAFT_BEDROCK, null, null));
    }

    private ProjectDetailsProjection model() {

        return new ProjectDetailsProjection("name", GameType.MINECRAFT_JAVA, ProjectStatus.ACTIVE, ProjectState.ACTIVE);
    }
}
