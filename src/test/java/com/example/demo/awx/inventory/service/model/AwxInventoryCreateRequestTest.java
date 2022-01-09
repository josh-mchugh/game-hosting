package com.example.demo.awx.inventory.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxInventoryCreateRequestTest {

    @Test
    public void whenModelHasAwxOrganizationIdThenReturnAwxOrganizationId() {

        AwxInventoryCreateRequest model = AwxInventoryCreateRequest.builder()
                .awxOrganizationId("awxOrganizationId")
                .build();

        Assertions.assertEquals("awxOrganizationId", model.getAwxOrganizationId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxInventoryCreateRequest model = AwxInventoryCreateRequest.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxInventoryCreateRequest model = AwxInventoryCreateRequest.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxInventoryCreateRequest model = AwxInventoryCreateRequest.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelToString() {

        AwxInventoryCreateRequest model = model();

        String expected = "AwxInventoryCreateRequest(awxOrganizationId=awxOrganizationId, awxId=1, name=name, description=description)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxInventoryCreateRequest model = model();

        Assertions.assertEquals(-249924983, model.hashCode());
    }

    @Test
    public void whenModelToEquals() {

        AwxInventoryCreateRequest model1 = model();
        AwxInventoryCreateRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxInventoryCreateRequest model = model();

        Assertions.assertNotEquals(model, AwxInventoryCreateRequest.builder().build());
    }

    private AwxInventoryCreateRequest model() {

        return AwxInventoryCreateRequest.builder()
                .awxOrganizationId("awxOrganizationId")
                .awxId(1L)
                .name("name")
                .description("description")
                .build();
    }
}
