package com.example.demo.framework.properties;

import com.example.demo.ovh.credential.entity.CredentialType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "ovh")
public class OvhConfig {

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
    @NotNull private List<OvhConfig.SshKeyConfig> sshKeyConfigs;

    @Data
    public static class SshKeyConfig {

        private String name;
        private CredentialType type;
        private String publicKey;
        private String privateKey;
    }
}
