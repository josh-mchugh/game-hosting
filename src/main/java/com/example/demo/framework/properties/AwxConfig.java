package com.example.demo.framework.properties;

import com.example.demo.awx.credential.entity.AwxCredentialType;
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
@ConfigurationProperties(prefix = "awx")
public class AwxConfig {

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
