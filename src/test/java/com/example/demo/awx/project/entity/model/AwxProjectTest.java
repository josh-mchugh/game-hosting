package com.example.demo.awx.project.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxProjectTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxProject model = AwxProject.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxProject model = AwxProject.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxProject model = AwxProject.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxProject model = AwxProject.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasScmTypeThenReturnScmType() {

        AwxProject model = AwxProject.builder()
                .scmType("scmType")
                .build();

        Assertions.assertEquals("scmType", model.getScmType());
    }

    @Test
    public void whenModelHasScmUrlThenReturnScmUrl() {

        AwxProject model = AwxProject.builder()
                .scmUrl("scmUrl")
                .build();

        Assertions.assertEquals("scmUrl", model.getScmUrl());
    }

    @Test
    public void whenModelHasScmBranchThenReturnScmBranch() {

        AwxProject model = AwxProject.builder()
                .scmBranch("scmBranch")
                .build();

        Assertions.assertEquals("scmBranch", model.getScmBranch());
    }

    @Test
    public void whenModelToString() {

        AwxProject model = model();

        String expected = "AwxProject(id=d278af13-6393-4440-85c7-39e27e20955d, awxId=1, name=name, description=description, scmType=scmType, scmUrl=scmUrl, scmBranch=scmBranch)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxProject model = model();

        Assertions.assertEquals(-823449315, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxProject model1 = model();
        AwxProject model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxProject model = model();

        Assertions.assertNotEquals(model, AwxProject.builder().build());
    }

    private AwxProject model() {

        return AwxProject.builder()
                .id(UUID.fromString("d278af13-6393-4440-85c7-39e27e20955d"))
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("scmType")
                .scmUrl("scmUrl")
                .scmBranch("scmBranch")
                .build();
    }
}
