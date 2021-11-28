package com.example.demo.framework.config;

import com.example.demo.email.entity.EmailTemplateType;
import com.example.demo.email.templates.EmailTemplate;
import com.example.demo.framework.properties.AppConfig;
import com.example.demo.util.AppUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class BeanUtilConfig {

    private final AppConfig appConfig;

    @Autowired(required = false)
    private Map<String, EmailTemplate> templates;

    public BeanUtilConfig(AppConfig appConfig) {

        this.appConfig = appConfig;
    }

    @Bean(name = "appUrlUtil")
    public AppUrlUtil appUrlUtil() {

        return new AppUrlUtil(appConfig.getUrl());
    }

    @Bean
    public Map<EmailTemplateType, EmailTemplate> emailTemplates() {

        return templates.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getValue().type(),
                        Map.Entry::getValue,
                        (l, r) -> {
                            throw new IllegalArgumentException(String.format("Duplicate keys %s and %s", l, r));
                        },
                        () -> new EnumMap<>(EmailTemplateType.class)
                    ));

    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Bean
    public SpringDataDialect springDataDialect() {

        return new SpringDataDialect();
    }
}
