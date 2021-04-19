package com.example.demo.awx.credential.feign.model;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxCredentialApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AwxCredentialApi model = new AwxCredentialApi();
        model.setId(1L);

        Assertions.assertEquals(1L, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxCredentialApi model = new AwxCredentialApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxCredentialApi model = new AwxCredentialApi();
        model.setDescription("description");

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasOrganizationIdThenOrganizationId() {

        AwxCredentialApi model = new AwxCredentialApi();
        model.setOrganizationId(1L);

        Assertions.assertEquals(1L, model.getOrganizationId());
    }

    @Test
    public void whenModelHasCredentialTypeThenReturnCredentialType() {

        AwxCredentialApi model = new AwxCredentialApi();
        model.setCredentialType(AwxCredentialType.MACHINE);

        Assertions.assertEquals(AwxCredentialType.MACHINE, model.getCredentialType());
    }

    @Test
    public void whenModelToString() {

        AwxCredentialApi model = new AwxCredentialApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setOrganizationId(1L);
        model.setCredentialType(AwxCredentialType.MACHINE);

        String expected = "AwxCredentialApi(id=1, name=name, description=description, organizationId=1, credentialType=MACHINE)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxCredentialApi model = model();

        Assertions.assertEquals(917217297, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxCredentialApi model1 = model();
        AwxCredentialApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEqual() {

        AwxCredentialApi model = model();

        Assertions.assertNotEquals(model, new AwxCredentialApi());
    }

    private AwxCredentialApi model() {

        AwxCredentialApi model = new AwxCredentialApi();
        model.setId(1L);
        model.setName("name");
        model.setDescription("description");
        model.setOrganizationId(1L);

        return model;
    }
}
