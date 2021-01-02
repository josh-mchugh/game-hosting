package com.example.demo.framework.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class BeanUtilConfigTest {

    @Autowired
    private BeanUtilConfig config;

    @Test
    public void whenConfigHasAppUrlUtilReturnAppUrlUtil() {

        Assertions.assertNotNull(config.appUrlUtil());
    }

    @Test
    public void whenConfigHasEmailTemplatesThenReturnEmailTemplates() {

        Assertions.assertNotNull(config.emailTemplates());
    }

    @Test
    public void whenConfigHasRestTemplateThenReturnRestTemplate() {

        Assertions.assertNotNull(config.restTemplate());
    }
}
