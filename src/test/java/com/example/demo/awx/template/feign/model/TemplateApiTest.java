package com.example.demo.awx.template.feign.model;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TemplateApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        TemplateApi model = new TemplateApi();
        model.setId(1L);

        Assertions.assertEquals(1L, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        TemplateApi model = new TemplateApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        TemplateApi model = new TemplateApi();
        model.setDescription("description");

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasPlaybookThenReturnPlaybook() {

        TemplateApi model = new TemplateApi();
        model.setPlaybook("playbook");

        Assertions.assertEquals("playbook", model.getPlaybook());
    }

    @Test
    public void whenModelHasLimitThenReturnLimit() {

        TemplateApi model = new TemplateApi();
        model.setLimit("limit");

        Assertions.assertEquals("limit", model.getLimit());
    }

    @Test
    public void whenModelHasInventoryIdThenReturnInventoryId() {

        TemplateApi model = new TemplateApi();
        model.setInventoryId(1L);

        Assertions.assertEquals(1L, model.getInventoryId());
    }

    @Test
    public void whenModelHasProjectIdThenReturnProjectId() {

        TemplateApi model = new TemplateApi();
        model.setProjectId(1L);

        Assertions.assertEquals(1L, model.getProjectId());
    }

    @Test
    public void whenModelHasJobTypeThenReturnJobType() {

        TemplateApi model = new TemplateApi();
        model.setJobType(TemplateJobType.RUN);

        Assertions.assertEquals(TemplateJobType.RUN, model.getJobType());
    }

    @Test
    public void whenModelHasVerbosityThenReturnVerbosity() {

        TemplateApi model = new TemplateApi();
        model.setVerbosity(TemplateVerbosity.NORMAL);

        Assertions.assertEquals(TemplateVerbosity.NORMAL, model.getVerbosity());
    }

    @Test
    public void whenModelToString() {

        TemplateApi model = model();

        String expected = "TemplateApi(id=1, name=name, description=description, playbook=playbook, limit=limit, inventoryId=1, projectId=1, jobType=RUN, verbosity=NORMAL)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        TemplateApi model = new TemplateApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setPlaybook("playbook");
        model.setLimit("limit");
        model.setInventoryId(1L);
        model.setProjectId(1L);

        Assertions.assertEquals(754622243, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        TemplateApi model1 = model();
        TemplateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        TemplateApi model = model();

        Assertions.assertNotEquals(model, new TemplateApi());
    }

    private TemplateApi model() {

        TemplateApi model = new TemplateApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setPlaybook("playbook");
        model.setLimit("limit");
        model.setInventoryId(1L);
        model.setProjectId(1L);
        model.setJobType(TemplateJobType.RUN);
        model.setVerbosity(TemplateVerbosity.NORMAL);

        return model;
    }
}
