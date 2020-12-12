package com.example.demo.awx.organization.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrganizationApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        OrganizationApi model = new OrganizationApi();
        model.setId(1L);

        Assertions.assertEquals(1L, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        OrganizationApi model = new OrganizationApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        OrganizationApi model = new OrganizationApi();
        model.setDescription("description");

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelToString() {

        OrganizationApi model = model();

        String expected = "OrganizationApi(id=1, name=name, description=description)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        OrganizationApi model = model();

        Assertions.assertEquals(-1525288479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        OrganizationApi model1 = model();
        OrganizationApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        OrganizationApi model = model();

        Assertions.assertNotEquals(model, new OrganizationApi());
    }

    private OrganizationApi model() {

        OrganizationApi model = new OrganizationApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");

        return model;
    }
}
