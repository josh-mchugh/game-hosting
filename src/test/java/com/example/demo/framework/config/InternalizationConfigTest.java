package com.example.demo.framework.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InternalizationConfigTest {

    @Test
    public void whenConfigHasLocaleResolverThenReturnLocaleResolver() {

        InternalizationConfig config = new InternalizationConfig();

        Assertions.assertNotNull(config.localeResolver());
    }

    @Test
    public void whenConfigHasLocaleChangeInterceptorThenReturnLocalChangeInterceptor() {

        InternalizationConfig config = new InternalizationConfig();

        Assertions.assertNotNull(config.localeChangeInterceptor());
    }

    @Test
    public void whenConfigHasMessageSourceThenReturnMessageSource() {

        InternalizationConfig config = new InternalizationConfig();

        Assertions.assertNotNull(config.messageSource());
    }

    @Test
    public void whenConfigHasValidatorFactoryBeanThenReturnValidatorFactoryBean() {

        InternalizationConfig config = new InternalizationConfig();

        Assertions.assertNotNull(config.validatorFactoryBean(config.messageSource()));
    }
}
