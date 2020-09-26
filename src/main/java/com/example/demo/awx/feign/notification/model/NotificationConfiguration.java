package com.example.demo.awx.feign.notification.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class NotificationConfiguration {

    private String url;
    private Map<String, Object> headers;

    public NotificationConfiguration(String url) {

        this.url = url;
        this.headers = new HashMap<>();
    }
}
