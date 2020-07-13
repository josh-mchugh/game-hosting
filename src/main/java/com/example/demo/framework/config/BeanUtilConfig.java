package com.example.demo.framework.config;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.util.AppUrlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanUtilConfig {

    private final AppConfig appConfig;

    @Bean(name = "appUrlUtil")
    public AppUrlUtil appUrlUtil() {

        return new AppUrlUtil(appConfig.getUrl());
    }
}
