package com.example.demo.awx.organization.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxOrganizationTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AwxOrganization model = AwxOrganization.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxOrganization model = AwxOrganization.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxOrganization model = AwxOrganization.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnHasDescription() {

        AwxOrganization model = AwxOrganization.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelToString() {

        AwxOrganization model = model();

        String expected = "AwxOrganization(id=id, awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxOrganization model = model();

        Assertions.assertEquals(-1501495844, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxOrganization model1 = model();
        AwxOrganization model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxOrganization model = model();

        Assertions.assertNotEquals(model, AwxOrganization.builder().build());
    }

    private AwxOrganization model() {

        return AwxOrganization.builder()
                .id("id")
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
