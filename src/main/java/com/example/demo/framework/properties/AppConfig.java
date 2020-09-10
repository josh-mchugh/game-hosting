package com.example.demo.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String url;
    private boolean enableSeedData;
    private AdminUser adminUser;
    private Email email;
    private Password password;

    @Data
    public static class AdminUser {

        @NotBlank private String username;
        @NotBlank private String password;
    }

    @Data
    public static class Email {

        private String noReplyAddress;
        private String supportAddress;
        private String schedulerDelay;
        private String schedulerInitialDelay;
        private Integer pagingSize;
    }

    @Data
    public static class Password {

        private String recoverySchedulerDelay;
        private String recoverySchedulerInitialDelay;
        private Long recoveryExpirationOffset;
    }
}
