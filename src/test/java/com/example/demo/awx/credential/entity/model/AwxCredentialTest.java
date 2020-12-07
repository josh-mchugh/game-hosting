package com.example.demo.awx.credential.entity.model;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxCredentialTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AwxCredential model = AwxCredential.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasCredentialIdThenReturnCredentialId() {

        AwxCredential model = AwxCredential.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxCredential model = AwxCredential.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxCredential model = AwxCredential.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasPrivateKeyThenReturnPrivateKey() {

        AwxCredential model = AwxCredential.builder()
                .privateKey("privateKey")
                .build();

        Assertions.assertEquals("privateKey", model.getPrivateKey());
    }

    @Test
    public void whenModelHasPassPhraseThenReturnPassPhrase() {

        AwxCredential model = AwxCredential.builder()
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals("passPhrase", model.getPassphrase());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        AwxCredential model = AwxCredential.builder()
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertEquals(AwxCredentialType.MACHINE, model.getType());
    }

    @Test
    public void whenModelToString() {

        AwxCredential model = AwxCredential.builder()
                .id("id")
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        String expected = "AwxCredential(id=id, awxId=1, name=name, description=description, privateKey=privateKey, passphrase=passPhrase, type=MACHINE)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxCredential model = model();

        Assertions.assertEquals(-1899840587, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxCredential model1 = model();
        AwxCredential model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxCredential model = model();

        Assertions.assertNotEquals(model, AwxCredential.builder().build());
    }

    private AwxCredential model() {

        return AwxCredential.builder()
                .id("id")
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .build();
    }
}
