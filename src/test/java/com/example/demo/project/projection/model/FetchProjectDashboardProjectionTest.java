package com.example.demo.project.projection.model;

import com.example.demo.game.entity.GameType;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectDashboardProjectionTest {

    @Test
    public void whenModelHasProjectsThenReturnProjects() {

        ProjectDashboardProjection project = new ProjectDashboardProjection(UUID.randomUUID().toString(), "name", null);
        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(project);

        FetchProjectDashboardProjection model = new FetchProjectDashboardProjection(projects);

        Assertions.assertEquals(ImmutableList.of(project), model.getProjects());
    }

    @Test
    public void whenModelToString() {

        FetchProjectDashboardProjection model = model();

        String expected = "FetchProjectDashboardProjection(projects=[ProjectDashboardProjection(id=ff9eeb3d-9c57-4f65-8a36-974b5ea37a38, name=name, gameType=MINECRAFT_JAVA)])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectDashboardProjection project = new ProjectDashboardProjection(UUID.fromString("ff9eeb3d-9c57-4f65-8a36-974b5ea37a38").toString(), "name", null);
        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(project);

        FetchProjectDashboardProjection model =  new FetchProjectDashboardProjection(projects);

        Assertions.assertEquals(1371490948, model.hashCode());
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

        ProjectDashboardProjection project = new ProjectDashboardProjection(UUID.fromString("ff9eeb3d-9c57-4f65-8a36-974b5ea37a38").toString(), "name", GameType.MINECRAFT_JAVA);
        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(project);

        return new FetchProjectDashboardProjection(projects);
    }
}
