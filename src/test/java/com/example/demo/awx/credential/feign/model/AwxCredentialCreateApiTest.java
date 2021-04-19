package com.example.demo.awx.credential.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxCredentialCreateApiTest {

    @Test
    public void whenModelHasNameThenReturnName() {
        
        AwxCredentialCreateApi model = AwxCredentialCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }
    
    @Test
    public void whenModelHasDescriptionThenReturnDescription() {
        
        AwxCredentialCreateApi model = AwxCredentialCreateApi.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelIsDefaultThenExpectBlankDescription() {

        AwxCredentialCreateApi model = AwxCredentialCreateApi.builder().build();

        Assertions.assertEquals("", model.getDescription());
    }

    @Test
    public void whenModelHasCredentialTypeThenReturnCredentialType() {

        AwxCredentialCreateApi model = AwxCredentialCreateApi.builder()
                .credentialType(1)
                .build();

        Assertions.assertEquals(1, model.getCredentialType());
    }

    @Test
    public void whenModelHasInputThenReturnInput() {

        AwxCredentialCreateApi.Input input = AwxCredentialCreateApi.Input.builder().build();

        AwxCredentialCreateApi model = AwxCredentialCreateApi.builder()
                .inputs(input)
                .build();

        Assertions.assertEquals(input, model.getInputs());
    }

    @Test
    public void whenInputHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialCreateApi.Input input = AwxCredentialCreateApi.Input.builder()
                .privateKey("privateKey")
                .build();

        Assertions.assertEquals("privateKey", input.getPrivateKey());
    }

    @Test
    public void whenInputHasPassPhraseThenReturnPassPhrase() {

        AwxCredentialCreateApi.Input input = AwxCredentialCreateApi.Input.builder()
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals("passPhrase", input.getPassphrase());
    }

    @Test
    public void whenInputToString() {

        AwxCredentialCreateApi.Input input = input();

        String expected = "AwxCredentialCreateApi.Input(privateKey=privateKey, passphrase=passPhrase)";

        Assertions.assertEquals(expected, input.toString());
    }

    @Test
    public void whenInputHashCode() {

        AwxCredentialCreateApi.Input input = input();

        Assertions.assertEquals(1150172535, input.hashCode());
    }

    @Test
    public void whenInputEquals() {

        AwxCredentialCreateApi.Input input1 = input();
        AwxCredentialCreateApi.Input input2 = input();

        Assertions.assertEquals(input1, input2);
    }

    @Test
    public void whenInputNotEquals() {

        AwxCredentialCreateApi.Input input = input();

        Assertions.assertNotEquals(input, AwxCredentialCreateApi.Input.builder().build());
    }

    @Test
    public void whenModelToString() {

        AwxCredentialCreateApi model = model();

        String expected = "AwxCredentialCreateApi(name=name, description=description, inputs=AwxCredentialCreateApi.Input(privateKey=privateKey, passphrase=passPhrase), credentialType=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxCredentialCreateApi model = model();

        Assertions.assertEquals(1352465490, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxCredentialCreateApi model1 = model();
        AwxCredentialCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxCredentialCreateApi model = model();

        Assertions.assertNotEquals(model, AwxCredentialCreateApi.builder().build());
    }

    private AwxCredentialCreateApi model() {

        return AwxCredentialCreateApi.builder()
                .name("name")
                .description("description")
                .inputs(input())
                .credentialType(1)
                .build();
    }

    private AwxCredentialCreateApi.Input input() {

        return AwxCredentialCreateApi.Input.builder()
                .passphrase("passPhrase")
                .privateKey("privateKey")
                .build();
    }
}
