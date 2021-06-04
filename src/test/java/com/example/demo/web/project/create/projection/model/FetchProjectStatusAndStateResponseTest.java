package com.example.demo.web.project.create.projection.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectStatusAndStateResponseTest {

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        FetchProjectStatusAndStateResponse model = new FetchProjectStatusAndStateResponse(ProjectStatus.CONFIG, null);

        Assertions.assertEquals(ProjectStatus.CONFIG, model.getStatus());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        FetchProjectStatusAndStateResponse model = new FetchProjectStatusAndStateResponse(null, ProjectState.CONFIG_REGION);

        Assertions.assertEquals(ProjectState.CONFIG_REGION, model.getState());
    }

    @Test
    public void whenModelToString() {

        FetchProjectStatusAndStateResponse model = model();

        String expected = "FetchProjectStatusAndStateResponse(status=CONFIG, state=CONFIG_REGION)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectStatusAndStateResponse model = new FetchProjectStatusAndStateResponse(null, null);

        Assertions.assertEquals(6061, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectStatusAndStateResponse model1 = model();
        FetchProjectStatusAndStateResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectStatusAndStateResponse model = model();

        Assertions.assertNotEquals(model, new FetchProjectStatusAndStateResponse(null, null));
    }

    private FetchProjectStatusAndStateResponse model() {

        return new FetchProjectStatusAndStateResponse(ProjectStatus.CONFIG, ProjectState.CONFIG_REGION);
    }
}
