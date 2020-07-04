package com.example.demo.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private Email email;

    @Data
    public static class Email {

        private String fromAddress;
        private Long schedulerDelay;
        private Integer pagingSize;
    }
}
