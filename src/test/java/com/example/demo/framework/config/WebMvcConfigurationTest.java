package com.example.demo.framework.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

public class WebMvcConfigurationTest {

    @Test
    public void whenConfigAddsInterceptorThenExpectNotException() {

        WebMvcConfiguration config = new WebMvcConfiguration(new LocaleChangeInterceptor());

        Assertions.assertDoesNotThrow(() -> config.addInterceptors(new InterceptorRegistry()));
    }
}
