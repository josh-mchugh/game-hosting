package com.example.demo.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String url;
    private Email email;
    private Password password;
    private Ovh ovh;

    @Data
    public static class Email {

        private String noReplyAddress;
        private String supportAddress;
        private Long schedulerDelay;
        private Integer pagingSize;
    }

    @Data
    public static class Password {

        private Long recoverySchedulerDelay;
        private Long recoveryExpirationOffset;
    }

    @Data
    public static class Ovh {

        private String baseUrl;
        private String appKey;
        private String appSecret;
        private String customerKey;
        private String projectId;
    }
}
