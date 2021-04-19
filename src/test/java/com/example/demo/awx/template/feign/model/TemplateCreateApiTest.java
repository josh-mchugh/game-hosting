package com.example.demo.awx.template.feign.model;

import com.example.demo.awx.template.entity.TemplateJobType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TemplateCreateApiTest {

    @Test
    public void whenModelHasInventoryIdThenReturnInventoryId() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .inventoryId(1L)
                .build();

        Assertions.assertEquals(1L, model.getInventoryId());
    }

    @Test
    public void whenModelHasProjectIdThenReturnProjectId() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .projectId(1L)
                .build();

        Assertions.assertEquals(1L, model.getProjectId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasJobTypeThenReturnJobType() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .jobType(TemplateJobType.RUN)
                .build();

        Assertions.assertEquals(TemplateJobType.RUN, model.getJobType());
    }

    @Test
    public void whenModelHasPlaybookThenReturnPlaybook() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .playbook("playbook")
                .build();

        Assertions.assertEquals("playbook", model.getPlaybook());
    }

    @Test
    public void whenModelHasLimitThenReturnLimit() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .limit("limit")
                .build();

        Assertions.assertEquals("limit", model.getLimit());
    }

    @Test
    public void whenModelHasVerbosityThenReturnVerbosity() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .verbosity(1)
                .build();

        Assertions.assertEquals(1, model.getVerbosity());
    }

    @Test
    public void whenModelToString() {

        TemplateCreateApi model = model();

        String expected = "TemplateCreateApi(inventoryId=1, projectId=1, name=name, description=description, jobType=RUN, playbook=playbook, limit=limit, verbosity=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        TemplateCreateApi model = TemplateCreateApi.builder()
                .inventoryId(1L)
                .projectId(1L)
                .name("name")
                .description("description")
                .playbook("playbook")
                .limit("limit")
                .verbosity(1)
                .build();

        Assertions.assertEquals(-631047820, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        TemplateCreateApi model1 = model();
        TemplateCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        TemplateCreateApi model = model();

        Assertions.assertNotEquals(model, TemplateCreateApi.builder().build());
    }

    private TemplateCreateApi model() {

        return TemplateCreateApi.builder()
                .inventoryId(1L)
                .projectId(1L)
                .name("name")
                .description("description")
                .jobType(TemplateJobType.RUN)
                .playbook("playbook")
                .limit("limit")
                .verbosity(1)
                .build();
    }
}
