package com.example.demo.web.project.create.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectCreateResponseTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectCreateResponse model = new ProjectCreateResponse(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateResponse model = model();

        String expected = "ProjectCreateResponse(id=ced08ef2-a858-43a8-92e0-9cc66c09e171)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateResponse model = model();

        Assertions.assertEquals(-1738428120, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateResponse model1 = model();
        ProjectCreateResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateResponse model = model();

        Assertions.assertNotEquals(model, new ProjectCreateResponse(null));
    }

    private ProjectCreateResponse model() {

        return new ProjectCreateResponse(UUID.fromString("ced08ef2-a858-43a8-92e0-9cc66c09e171"));
    }
}
