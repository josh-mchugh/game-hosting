package com.example.demo.web.dashboard.projection.service.model;

import com.example.demo.game.entity.GameType;
import com.example.demo.web.dashboard.projection.service.projection.ProjectDashboardProjection;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchDashboardDetailsResponseTest {

    @Test
    public void whenModelHasEmailVerifiedThenReturnEmailVerified() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .build();

        Assertions.assertTrue(model.isEmailVerified());
    }

    @Test
    public void whenModelHasHasProjectsThenReturnHasProjects() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .hasProjects(true)
                .build();

        Assertions.assertTrue(model.isHasProjects());
    }

    @Test
    public void whenModelHasProjectsThenReturnProjects() {

        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(new ProjectDashboardProjection(UUID.randomUUID().toString(), "name", GameType.MINECRAFT_JAVA));

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .projects(projects)
                .build();

        Assertions.assertEquals(projects, model.getProjects());
    }

    @Test
    public void whenModelDisplayEmailVerificationIsTrue() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(false)
                .build();

        Assertions.assertTrue(model.displayEmailVerification());
    }

    @Test
    public void whenModelDisplayEmailVerificationIsFalse() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .build();

        Assertions.assertFalse(model.displayEmailVerification());
    }

    @Test
    public void whenModelDisplayCreateServerIsTrue() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(false)
                .build();

        Assertions.assertTrue(model.displayCreateServer());
    }

    @Test
    public void whenModelDisplayServiceIsFalse() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .build();

        Assertions.assertFalse(model.displayCreateServer());
    }

    @Test
    public void whenModelDisplayProjectListIsTrue() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .build();

        Assertions.assertTrue(model.displayProjectList());
    }

    @Test
    public void whenModelDisplayProjectListIsFalse() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(false)
                .build();

        Assertions.assertFalse(model.displayProjectList());
    }

    @Test
    public void whenModelToString() {

        FetchDashboardDetailsResponse model = model();

        String expected = "FetchDashboardDetailsResponse(emailVerified=true, hasProjects=true, projects=[ProjectDashboardProjection(id=8bd3a4b5-b822-40d7-9dca-e3943fc0274b, name=name, gameType=MINECRAFT_JAVA)])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchDashboardDetailsResponse model = FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .projects(ImmutableList.of())
                .build();

        Assertions.assertEquals(485040, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchDashboardDetailsResponse model1 = model();
        FetchDashboardDetailsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchDashboardDetailsResponse model = model();

        Assertions.assertNotEquals(model, FetchDashboardDetailsResponse.builder().build());
    }

    private FetchDashboardDetailsResponse model() {

        ImmutableList<ProjectDashboardProjection> projects = ImmutableList.of(new ProjectDashboardProjection(UUID.fromString("8bd3a4b5-b822-40d7-9dca-e3943fc0274b").toString(), "name", GameType.MINECRAFT_JAVA));

        return FetchDashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(true)
                .projects(projects)
                .build();
    }
}
