package com.example.demo.framework.security.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordEncoderConfigTest {

    @Test
    public void whenConfigHasPasswordEncoderThenReturnPasswordEncoder() {

        Assertions.assertNotNull(new PasswordEncoderConfig().passwordEncoder());
    }
}
