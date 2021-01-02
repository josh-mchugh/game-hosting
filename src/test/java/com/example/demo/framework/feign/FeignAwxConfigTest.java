package com.example.demo.framework.feign;

import com.example.demo.framework.properties.AwxConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeignAwxConfigTest {

    @Test
    public void whenConfigHasRequestInterceptorThenReturnRequestInterceptor() {

        FeignAwxConfig config = new FeignAwxConfig();

        AwxConfig awxConfig = new AwxConfig();
        awxConfig.setUsername("username");
        awxConfig.setPassword("password");

        Assertions.assertNotNull(config.authRequestInterceptor(awxConfig));
    }
}
