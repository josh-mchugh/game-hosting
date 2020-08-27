package com.example.demo.framework.properties;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private Ovh ovh;
    private Awx awx;

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
        @NotNull private List<SshKeyConfig> sshKeyConfigs;

        @Data
        public static class SshKeyConfig {

            private String name;
            private CredentialType type;
            private String publicKey;
            private String privateKey;
        }
    }

    @Data
    public static class Awx {

        @NotBlank private String baseUrl;
        @NotBlank private String username;
        @NotBlank private String password;
        @NotNull private Organization organization;
        @NotNull private List<Credential> credentials;
        @NotNull private Project project;
        @NotNull private Inventory inventory;

        @Data
        public static class Organization {

            @NotNull private Long id;
        }

        @Data
        public static class Credential {

            @NotBlank private String name;
            @NotNull private AwxCredentialType type;
            @NotBlank private String privateKey;
            private String passphrase;
        }

        @Data
        public static class Project {

            @NotBlank private String name;
            @NotBlank private String description;
            @NotBlank private String scmType;
            @NotBlank private String scmUrl;
            @NotBlank private String scmBranch;
            @NotBlank private String credentialName;
        }

        @Data
        public static class Inventory {

            @NotBlank private String name;
            private String description;
        }
    }
}
