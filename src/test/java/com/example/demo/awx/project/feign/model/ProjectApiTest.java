package com.example.demo.awx.project.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        ProjectApi model = new ProjectApi();
        model.setId(1L);

        Assertions.assertEquals(1L, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        ProjectApi model = new ProjectApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        ProjectApi model = new ProjectApi();
        model.setDescription("description");

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasScmTypeThenReturnScmType() {

        ProjectApi model = new ProjectApi();
        model.setScmType("scmType");

        Assertions.assertEquals("scmType", model.getScmType());
    }

    @Test
    public void whenModelHasScmUrlThenReturnScmUrl() {

        ProjectApi model = new ProjectApi();
        model.setScmUrl("scmUrl");

        Assertions.assertEquals("scmUrl", model.getScmUrl());
    }

    @Test
    public void whenModelHasScmBranchThenReturnScmBranch() {

        ProjectApi model = new ProjectApi();
        model.setScmBranch("scmBranch");

        Assertions.assertEquals("scmBranch", model.getScmBranch());
    }

    @Test
    public void whenModelHasCredentialIdThenReturnCredentialId() {

        ProjectApi model = new ProjectApi();
        model.setCredentialId(1L);

        Assertions.assertEquals(1L, model.getCredentialId());
    }

    @Test
    public void whenModelHasOrganizationIdThenReturnOrganizationId() {

        ProjectApi model = new ProjectApi();
        model.setOrganizationId(1L);

        Assertions.assertEquals(1L, model.getOrganizationId());
    }

    @Test
    public void whenModelToString() {

        ProjectApi model = model();

        String expected = "ProjectApi(id=1, name=name, description=description, scmType=scmType, scmUrl=scmUrl, scmBranch=scmBranch, credentialId=1, organizationId=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectApi model = model();

        Assertions.assertEquals(241115179, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectApi model1 = model();
        ProjectApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectApi  model = model();

        Assertions.assertNotEquals(model, new ProjectApi());
    }

    private ProjectApi model() {

        ProjectApi model = new ProjectApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setScmType("scmType");
        model.setScmUrl("scmUrl");
        model.setScmBranch("scmBranch");
        model.setCredentialId(1L);
        model.setOrganizationId(1L);

        return model;
    }
}
