package com.example.demo.web.dashboard.service.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectDashboardProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectDashboardProjection model = new ProjectDashboardProjection(id.toString(), null, null, null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        ProjectDashboardProjection model = new ProjectDashboardProjection(UUID.randomUUID().toString(), "name", null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        ProjectDashboardProjection model = new ProjectDashboardProjection(UUID.randomUUID().toString(), null, GameType.MINECRAFT_JAVA, null);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelHasProjectStatusThenReturnProjectStatus() {

        ProjectDashboardProjection model = new ProjectDashboardProjection(UUID.randomUUID().toString(), null, null, ProjectStatus.CONFIG);

        Assertions.assertEquals(ProjectStatus.CONFIG, model.getStatus());
    }

    @Test
    public void whenModelToString() {

        ProjectDashboardProjection model = model();

        String expected = "ProjectDashboardProjection(id=0a36c490-065e-46f1-9dd1-022a8dc05032, name=name, gameType=MINECRAFT_JAVA, status=CONFIG)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectDashboardProjection model = new ProjectDashboardProjection(UUID.fromString("0a36c490-065e-46f1-9dd1-022a8dc05032").toString(), "name", null, null);

        Assertions.assertEquals(-810361533, model.hashCode());
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

        Assertions.assertNotEquals(model, new ProjectDashboardProjection(UUID.randomUUID().toString(), null, null, null));
    }

    private ProjectDashboardProjection model() {

        return new ProjectDashboardProjection(UUID.fromString("0a36c490-065e-46f1-9dd1-022a8dc05032").toString(), "name", GameType.MINECRAFT_JAVA, ProjectStatus.CONFIG);
    }
}
