package com.example.demo.framework.database.converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.collections4.MapUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;

@Converter
public class MapConverter implements AttributeConverter<Map<String, Object>, String> {


    @SneakyThrows(value = {JsonParseException.class, JsonProcessingException.class})
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {

        if(MapUtils.isEmpty(attribute)) {

            return null;
        }

        return new ObjectMapper().writeValueAsString(attribute);
    }

    @SneakyThrows(value = {JsonProcessingException.class})
    @Override
    public Map<String, Object> convertToEntityAttribute(String json) {

        if(json == null) {

            return null;
        }

        TypeReference<Map<String, Object>> type = new TypeReference<Map<String, Object>>() {};

        return new ObjectMapper().readValue(json, type);
    }
}
