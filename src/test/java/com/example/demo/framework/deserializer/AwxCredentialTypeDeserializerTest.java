package com.example.demo.framework.deserializer;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.feign.credential.model.CredentialApi;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AwxCredentialTypeDeserializerTest {

    @Test
    public void whenCredentialTypeIsValidThenReturnType() throws IOException {

        String value = "{\"credential_type\": \"1\"}";

        CredentialApi response = new ObjectMapper().readValue(value, CredentialApi.class);

        Assertions.assertEquals(AwxCredentialType.MACHINE, response.getCredentialType());
    }

    @Test
    public void whenCredentialTypeIsInvalidValueThenReturnType() {

        String value = "{\"credential_type\": \"999\"}";

        Assertions.assertThrows(JsonMappingException.class, () -> new ObjectMapper().readValue(value, CredentialApi.class));
    }


    @Test
    public void whenCredentialTypeIsInvalidTypeThenThrowException() {

        String value = "{\"credential_type\": \"asdf\"}";

       Assertions.assertThrows(InvalidFormatException.class, () -> new ObjectMapper().readValue(value, CredentialApi.class));
    }
}
