package com.example.demo.awx.organization.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxOrganizationCreateRequestTest {

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxOrganizationCreateRequest model = AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxOrganizationCreateRequest model = AwxOrganizationCreateRequest.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxOrganizationCreateRequest model = AwxOrganizationCreateRequest.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelToString() {

        AwxOrganizationCreateRequest model = model();

        String expected = "AwxOrganizationCreateRequest(awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxOrganizationCreateRequest model = model();

        Assertions.assertEquals(-1525288479, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxOrganizationCreateRequest model1 = model();
        AwxOrganizationCreateRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxOrganizationCreateRequest model = model();

        Assertions.assertNotEquals(model, AwxOrganizationCreateRequest.builder().build());
    }

    private AwxOrganizationCreateRequest model() {

        return AwxOrganizationCreateRequest.builder()
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
