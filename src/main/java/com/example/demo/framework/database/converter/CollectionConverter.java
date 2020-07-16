package com.example.demo.framework.database.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Collection;

@Converter
public class CollectionConverter implements AttributeConverter<Collection<Object>, String> {

    @SneakyThrows(JsonProcessingException.class)
    @Override
    public String convertToDatabaseColumn(Collection<Object> attribute) {

        if(attribute == null) {

            return  null;
        }

        return new ObjectMapper().writeValueAsString(attribute);
    }

    @SneakyThrows(value = JsonProcessingException.class)
    @Override
    public Collection<Object> convertToEntityAttribute(String json) {

        if(json == null) {

            return null;
        }

        TypeReference<Collection<Object>> type = new TypeReference<Collection<Object>>() {};

        return new ObjectMapper().readValue(json, type);
    }
}
