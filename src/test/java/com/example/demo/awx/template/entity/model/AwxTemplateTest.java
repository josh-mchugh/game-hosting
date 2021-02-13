package com.example.demo.awx.template.entity.model;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxTemplateTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxTemplate model = AwxTemplate.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxTemplate model = AwxTemplate.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxTemplate model = AwxTemplate.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxTemplate model = AwxTemplate.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        AwxTemplate model = AwxTemplate.builder()
                .type(TemplateJobType.RUN)
                .build();

        Assertions.assertEquals(TemplateJobType.RUN, model.getType());
    }

    @Test
    public void whenModelHasVerbosityThenReturnVerbosity() {

        AwxTemplate model = AwxTemplate.builder()
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        Assertions.assertEquals(TemplateVerbosity.NORMAL, model.getVerbosity());
    }

    @Test
    public void whenModelToString() {

        AwxTemplate model = model();

        String expected = "AwxTemplate(id=1d171a9c-d6eb-4ac3-86f4-e3f723bd5e42, awxId=1, name=name, description=description, type=RUN, verbosity=NORMAL)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxTemplate model = AwxTemplate.builder()
                .id(UUID.fromString("1d171a9c-d6eb-4ac3-86f4-e3f723bd5e42"))
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertEquals(1970595353, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxTemplate model1 = model();
        AwxTemplate model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxTemplate model = model();

        Assertions.assertNotEquals(model, AwxTemplate.builder().build());
    }

    private AwxTemplate model() {

        return AwxTemplate.builder()
                .id(UUID.fromString("1d171a9c-d6eb-4ac3-86f4-e3f723bd5e42"))
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();
    }
}
