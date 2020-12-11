package com.example.demo.awx.notification.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class NotificationConfigurationTest {

    @Test
    public void whenModelHasUrlThenReturnUrl() {

        NotificationConfiguration model = NotificationConfiguration.builder()
                .url("url")
                .build();

        Assertions.assertEquals("url", model.getUrl());
    }

    @Test
    public void whenModelHasHeaderThenReturnHeaders() {

        Map<String, Object> headers = new HashMap<>();

        NotificationConfiguration model = NotificationConfiguration.builder()
                .headers(headers)
                .build();

        Assertions.assertEquals(headers, model.getHeaders());
    }

    @Test
    public void whenModelHasDefaultHeaderThenReturnHeaders() {

        NotificationConfiguration model = NotificationConfiguration.builder().build();

        Assertions.assertNotNull(model.getHeaders());
    }

    @Test
    public void whenModelToString() {

        NotificationConfiguration model = model();

        String expected = "NotificationConfiguration(url=url, headers={header=value})";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        NotificationConfiguration model = model();

        Assertions.assertEquals(-1308556758, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        NotificationConfiguration model1 = model();
        NotificationConfiguration model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        NotificationConfiguration model = model();

        Assertions.assertNotEquals(model, NotificationConfiguration.builder().build());
    }

    private NotificationConfiguration model() {

        Map<String, Object> headers = new HashMap<>();
        headers.put("header", "value");

        return NotificationConfiguration.builder()
                .url("url")
                .headers(headers)
                .build();
    }
}
