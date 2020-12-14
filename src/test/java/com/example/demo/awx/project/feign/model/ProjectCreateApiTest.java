package com.example.demo.awx.project.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectCreateApiTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        ProjectCreateApi model = ProjectCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        ProjectCreateApi model = ProjectCreateApi.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasScmTypeThenReturnScmType() {

        ProjectCreateApi model = ProjectCreateApi.builder()
                .scmType("scmType")
                .build();

        Assertions.assertEquals("scmType", model.getScmType());
    }

    @Test
    public void whenModelHasScmUrlThenReturnScmUrl() {

        ProjectCreateApi model = ProjectCreateApi.builder()
                .scmUrl("scmUrl")
                .build();

        Assertions.assertEquals("scmUrl", model.getScmUrl());
    }

    @Test
    public void whenModelHasScmBranchThenReturnScmBranch() {

        ProjectCreateApi model = ProjectCreateApi.builder()
                .scmBranch("scmBranch")
                .build();

        Assertions.assertEquals("scmBranch", model.getScmBranch());
    }

    @Test
    public void whenModelHasCredentialThenReturnCredential() {

        ProjectCreateApi model = ProjectCreateApi.builder()
                .credentialId(1L)
                .build();

        Assertions.assertEquals(1L, model.getCredentialId());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateApi model = model();

        String expected = "ProjectCreateApi(name=name, description=description, scmType=scmType, scmUrl=scmUrl, scmBranch=scmBranch, credentialId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateApi model = model();

        Assertions.assertEquals(249751883, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateApi model1 = model();
        ProjectCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateApi model = model();

        Assertions.assertNotEquals(model, ProjectCreateApi.builder().build());
    }

    private ProjectCreateApi model() {

        return ProjectCreateApi.builder()
                .name("name")
                .description("description")
                .scmType("scmType")
                .scmUrl("scmUrl")
                .scmBranch("scmBranch")
                .credentialId(1L)
                .build();
    }
}
