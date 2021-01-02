package com.example.demo.framework.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class DateTimeDeserializerTest {

    @Test
    public void whenDeserializerThenReturnLocalDateTime() throws IOException {

        TestObj test = new ObjectMapper().readValue("{\"dateTime\":\"2020-12-22T17:21:24Z\"}", TestObj.class);

        Assertions.assertNotNull(test.getDateTime());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestObj {

        @JsonDeserialize(using = DateTimeDeserializer.class)
        private LocalDateTime dateTime;
    }
}
