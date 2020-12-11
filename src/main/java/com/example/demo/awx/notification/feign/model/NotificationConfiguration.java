package com.example.demo.awx.notification.feign.model;

import lombok.Builder;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
@Builder(builderClassName = "Builder")
public class NotificationConfiguration {

    String url;

    @lombok.Builder.Default
    Map<String, Object> headers = new HashMap<>();
}
