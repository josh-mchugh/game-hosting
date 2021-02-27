package com.example.demo.web.awx.service.model;

import com.example.demo.web.awx.service.projection.ProjectProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectByAwxIdResponseTest {

    @Test
    public void whenModelHasProjectThenReturnProject() {

        UUID id = UUID.randomUUID();

        FetchProjectByAwxIdResponse model = new FetchProjectByAwxIdResponse(new ProjectProjection(id.toString(), 1L));

        Assertions.assertEquals(new ProjectProjection(id.toString(), 1L), model.getProject());
    }

    @Test
    public void whenModelToString() {

        FetchProjectByAwxIdResponse model = model();

        String expected = "FetchProjectByAwxIdResponse(project=ProjectProjection(id=3acebc83-c7f5-4625-b3e0-7afe1c256db6, awxId=1))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectByAwxIdResponse model = model();

        Assertions.assertEquals(549512111, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectByAwxIdResponse model1 = model();
        FetchProjectByAwxIdResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectByAwxIdResponse model = model();

        Assertions.assertNotEquals(model, new FetchProjectByAwxIdResponse(new ProjectProjection(UUID.randomUUID().toString(), null)));
    }

    private FetchProjectByAwxIdResponse model() {

        return new FetchProjectByAwxIdResponse(new ProjectProjection(UUID.fromString("3acebc83-c7f5-4625-b3e0-7afe1c256db6").toString(), 1L));
    }
}
