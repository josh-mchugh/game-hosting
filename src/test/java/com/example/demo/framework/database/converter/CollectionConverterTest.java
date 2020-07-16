package com.example.demo.framework.database.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionConverterTest {

    @Test
    public void testConvertToDatabaseColumnValid() throws JsonProcessingException {

        CollectionConverter converter = new CollectionConverter();
        String convertedValue = converter.convertToDatabaseColumn(buildCollection());

        Assertions.assertEquals(mapToString(), convertedValue);
    }

    @Test
    public void testConvertToDatabaseColumnNull() {

        CollectionConverter converter = new CollectionConverter();
        String convertedValue = converter.convertToDatabaseColumn(null);

        Assertions.assertNull(convertedValue);
    }

    @Test
    public void testCovertToEntityAttributeValid() throws JsonProcessingException {

        CollectionConverter converter = new CollectionConverter();
        Collection<Object> convertedValue = converter.convertToEntityAttribute(mapToString());

        Assertions.assertEquals(buildCollection(), convertedValue);
    }

    private Collection<Object> buildCollection() {

        Collection<Object> collection = new ArrayList<>();
        collection.add("test string");
        collection.add(100_000_000);
        collection.add(1.999_999d);
        collection.add(true);

        return collection;
    }

    private String mapToString() throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(buildCollection());
    }
}
