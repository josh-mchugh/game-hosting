package com.example.demo.util;

public interface MessageService {

    String getMessage(String key);

    String getMessage(String key, Object... args);
}
