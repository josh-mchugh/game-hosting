package com.example.demo.framework.database.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapConverterTest {

    @Test
    public void testConvertToDatabaseColumnValid() throws JsonProcessingException {

        MapConverter converter = new MapConverter();
        String convertedValue = converter.convertToDatabaseColumn(buildMap());

        Assertions.assertEquals(mapToString(), convertedValue);
    }

    @Test
    public void testConvertToDatabaseColumnNull() {

        MapConverter converter = new MapConverter();
        String convertedValue = converter.convertToDatabaseColumn(null);

        Assertions.assertNull(convertedValue);
    }

    @Test
    public void testCovertToEntityAttributeValid() throws JsonProcessingException {

        MapConverter converter = new MapConverter();
        Map<String, Object> convertedValue = converter.convertToEntityAttribute(mapToString());

        Assertions.assertEquals(buildMap(), convertedValue);
    }

    @Test
    public void testCovertToEntityAttributeNull() throws JsonProcessingException {

        MapConverter converter = new MapConverter();
        Map<String, Object> convertedValue = converter.convertToEntityAttribute(null);

        Assertions.assertNull(convertedValue);
    }

    private Map<String, Object> buildMap() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("testString", "test string");
        map.put("testNumber", 100_000_000);
        map.put("testDouble", 1.999_999d);
        map.put("boolean", true);

        return map;
    }

    private String mapToString() throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(buildMap());
    }
}
