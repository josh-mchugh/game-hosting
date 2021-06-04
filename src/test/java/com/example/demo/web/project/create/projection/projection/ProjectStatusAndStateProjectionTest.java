package com.example.demo.web.project.create.projection.projection;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectStatusAndStateProjectionTest {

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        ProjectStatusAndStateProjection model = new ProjectStatusAndStateProjection(ProjectStatus.CONFIG, null);

        Assertions.assertEquals(ProjectStatus.CONFIG, model.getStatus());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        ProjectStatusAndStateProjection model = new ProjectStatusAndStateProjection(null, ProjectState.CONFIG_REGION);

        Assertions.assertEquals(ProjectState.CONFIG_REGION, model.getState());
    }

    @Test
    public void whenModelToString() {

        ProjectStatusAndStateProjection model = model();

        String expected = "ProjectStatusAndStateProjection(status=CONFIG, state=CONFIG_REGION)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectStatusAndStateProjection model = new ProjectStatusAndStateProjection(null, null);

        Assertions.assertEquals(6061, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectStatusAndStateProjection model1 = model();
        ProjectStatusAndStateProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectStatusAndStateProjection model = model();

        Assertions.assertNotEquals(model, new ProjectStatusAndStateProjection(null, null));
    }

    private ProjectStatusAndStateProjection model() {

        return new ProjectStatusAndStateProjection(ProjectStatus.CONFIG, ProjectState.CONFIG_REGION);
    }
}
