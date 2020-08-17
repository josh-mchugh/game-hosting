package com.example.demo.framework.properties;

import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String url;
    private boolean enableSeedData;
    private AdminUser adminUser;
    private Email email;
    private Password password;
    private Ovh ovh;

    @Data
    public static class AdminUser {
        private String username;
        private String password;
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

    @Data
    public static class Ovh {

        private String baseUrl;
        private String appKey;
        private String appSecret;
        private String customerKey;
        private String projectId;
        private String regionSchedulerDelay;
        private String regionSchedulerInitialDelay;
        private String flavorSchedulerDelay;
        private String flavorSchedulerInitialDelay;
        private String imageSchedulerDelay;
        private String imageSchedulerInitialDelay;
        private List<SshKeyConfig> sshKeyConfigs;

        @Data
        public static class SshKeyConfig {

            private String name;
            private CredentialType type;
            private String publicKey;
            private String privateKey;
        }
    }
}
