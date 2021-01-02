package com.example.demo.framework.feign;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultFeignConfigurationTest {

    @Test
    public void whenConfigHasHttpClientReturnHttpClient() {

        DefaultFeignConfiguration config = new DefaultFeignConfiguration();

        Assertions.assertNotNull(config.httpClient());
    }
}
