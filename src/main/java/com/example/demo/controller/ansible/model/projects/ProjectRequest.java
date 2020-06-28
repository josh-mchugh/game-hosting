package com.example.demo.controller.ansible.model.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("local_path")
    private String localPath;

    @JsonProperty("scm_type")
    private String scmType;

    @JsonProperty("scm_url")
    private String scmUrl;

    @JsonProperty("scm_branch")
    private String scmBranch;

    @JsonProperty("scm_refspec")
    private String scmRefspec;

    @JsonProperty("scm_clean")
    private Boolean scmClean = false;

    @JsonProperty("scm_delete_on_update")
    private Boolean scmDeleteOnUpdate = false;

    @JsonProperty("credential")
    private Long credentialId;

    @JsonProperty("timeout")
    private Long timeout = 0L;

    @JsonProperty("organization")
    private Long organizationId;

    @JsonProperty("scm_update_on_launch")
    private Boolean scmUpdateOnLaunch = false;

    @JsonProperty("scm_update_cache_timeout")
    private Long scmUpdateCacheTimeout = 0L;

    @JsonProperty("allow_override")
    private Boolean allowOverride = false;

    @JsonProperty("custom_virtualenv")
    private String customVirtualEnv = "";
}
