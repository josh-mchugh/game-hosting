package com.example.demo.web.dashboard.projection.service.model;

import com.example.demo.game.entity.GameType;
import com.example.demo.project.projection.model.ProjectDashboardProjection;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DashboardDetailsResponseTest {

    @Test
    public void whenModelHasEmailVerifiedThenReturnEmailVerified() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .build();

        Assertions.assertTrue(model.isEmailVerified());
    }

    @Test
    public void whenModelHasHasProjectsThenReturnHasProjects() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .hasProjects(true)
                .build();

        Assertions.assertTrue(model.isHasProjects());
    }

    @Test
    public void whenModelHasProjectsThenReturnProjects() {

        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(new ProjectDashboardProjection("id", "name", GameType.MINECRAFT_JAVA));

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .projects(projects)
                .build();

        Assertions.assertEquals(projects, model.getProjects());
    }

    @Test
    public void whenModelDisplayEmailVerificationIsTrue() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(false)
                .build();

        Assertions.assertTrue(model.displayEmailVerification());
    }

    @Test
    public void whenModelDisplayEmailVerificationIsFalse() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .build();

        Assertions.assertFalse(model.displayEmailVerification());
    }

    @Test
    public void whenModelDisplayCreateServerIsTrue() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(false)
                .build();

        Assertions.assertTrue(model.displayCreateServer());
    }

    @Test
    public void whenModelDisplayServiceIsFalse() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .build();

        Assertions.assertFalse(model.displayCreateServer());
    }

    @Test
    public void whenModelDisplayProjectListIsTrue() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .build();

        Assertions.assertTrue(model.displayProjectList());
    }

    @Test
    public void whenModelDisplayProjectListIsFalse() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(false)
                .build();

        Assertions.assertFalse(model.displayProjectList());
    }

    @Test
    public void whenModelToString() {

        DashboardDetailsResponse model = model();

        String expected = "DashboardDetailsResponse(emailVerified=true, hasProjects=true, projects=[ProjectDashboardProjection(id=id, name=name, gameType=MINECRAFT_JAVA)])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        DashboardDetailsResponse model = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .projects(ImmutableList.of())
                .build();

        Assertions.assertEquals(485040, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        DashboardDetailsResponse model1 = model();
        DashboardDetailsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        DashboardDetailsResponse model = model();

        Assertions.assertNotEquals(model, DashboardDetailsResponse.builder().build());
    }

    private DashboardDetailsResponse model() {

        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(new ProjectDashboardProjection("id", "name", GameType.MINECRAFT_JAVA));

        return DashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .projects(projects)
                .build();
    }
}
