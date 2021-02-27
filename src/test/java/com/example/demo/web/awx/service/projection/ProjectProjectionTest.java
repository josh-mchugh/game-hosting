package com.example.demo.web.awx.service.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectProjection model = new ProjectProjection(id.toString(), null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        ProjectProjection model = new ProjectProjection(UUID.randomUUID().toString(), 1L);

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelToString() {

        ProjectProjection model = model();

        String expected = "ProjectProjection(id=c53a097d-e82d-4f40-8f2f-6726a423613e, awxId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectProjection model = model();

        Assertions.assertEquals(1749538337, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectProjection model1 = model();
        ProjectProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectProjection model = model();

        Assertions.assertNotEquals(model, new ProjectProjection(UUID.randomUUID().toString(), null));
    }

    private ProjectProjection model() {

        return new ProjectProjection(UUID.fromString("c53a097d-e82d-4f40-8f2f-6726a423613e").toString(), 1L);
    }
}
