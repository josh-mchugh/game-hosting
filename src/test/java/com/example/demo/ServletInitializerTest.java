package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializerTest {

    @Test
    public void whenServletInitializerConfiguredThenExpectNoErrors() {

        ServletInitializer initializer = new ServletInitializer();

        Assertions.assertDoesNotThrow(() -> initializer.configure(new SpringApplicationBuilder()));
    }
}
