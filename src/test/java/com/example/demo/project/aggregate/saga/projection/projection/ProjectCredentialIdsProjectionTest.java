package com.example.demo.project.aggregate.saga.projection.projection;

import com.example.demo.project.aggregate.saga.projection.project.ProjectCredentialIdsProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectCredentialIdsProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectCredentialIdsProjection model = new ProjectCredentialIdsProjection(id.toString(), null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        ProjectCredentialIdsProjection model = new ProjectCredentialIdsProjection(UUID.randomUUID().toString(), "ovhId");

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelToString() {

        ProjectCredentialIdsProjection model = model();

        String expected = "ProjectCredentialIdsProjection(id=1bf48dda-5413-45d9-80af-94bd89ef595e, ovhId=ovhId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCredentialIdsProjection model = model();

        Assertions.assertEquals(1322372245, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCredentialIdsProjection model1 = model();
        ProjectCredentialIdsProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCredentialIdsProjection model = model();

        Assertions.assertNotEquals(model, new ProjectCredentialIdsProjection(UUID.randomUUID().toString(), null));
    }

    private ProjectCredentialIdsProjection model() {

        return new ProjectCredentialIdsProjection(UUID.fromString("1bf48dda-5413-45d9-80af-94bd89ef595e").toString(), "ovhId");
    }
}
