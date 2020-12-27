package com.example.demo.project.projection.model;

import com.example.demo.game.entity.GameType;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectDashboardProjectionTest {

    @Test
    public void whenModelHasProjectsThenReturnProjects() {

        ProjectDashboardProjection project = new ProjectDashboardProjection("id", "name", null);
        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(project);

        FetchProjectDashboardProjection model = new FetchProjectDashboardProjection(projects);

        Assertions.assertEquals(ImmutableList.of(project), model.getProjects());
    }

    @Test
    public void whenModelToString() {

        FetchProjectDashboardProjection model = model();

        String expected = "FetchProjectDashboardProjection(projects=[ProjectDashboardProjection(id=id, name=name, gameType=MINECRAFT_JAVA)])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectDashboardProjection project = new ProjectDashboardProjection("id", "name", null);
        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(project);

        FetchProjectDashboardProjection model =  new FetchProjectDashboardProjection(projects);

        Assertions.assertEquals(210932980, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectDashboardProjection model1 = model();
        FetchProjectDashboardProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectDashboardProjection model = model();

        Assertions.assertNotEquals(model, new FetchProjectDashboardProjection(ImmutableList.of()));
    }

    private FetchProjectDashboardProjection model() {

        ProjectDashboardProjection project = new ProjectDashboardProjection("id", "name", GameType.MINECRAFT_JAVA);
        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(project);

        return new FetchProjectDashboardProjection(projects);
    }
}
