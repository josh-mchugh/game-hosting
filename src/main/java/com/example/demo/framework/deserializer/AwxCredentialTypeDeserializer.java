package com.example.demo.framework.deserializer;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.EnumSet;

public class AwxCredentialTypeDeserializer extends JsonDeserializer<AwxCredentialType> {

    @Override
    public AwxCredentialType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Integer id = deserializationContext.readValue(jsonParser, Integer.class);

        return EnumSet.allOf(AwxCredentialType.class).stream()
                .filter(type -> type.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown Credential Type was parsed and cannot be associated"));
    }
}
