package com.example.demo.controller.ansible.model.projects;

import com.example.demo.controller.ansible.model.base.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends AbstractBase {

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
    private Boolean scmClean;

    @JsonProperty("scm_delete_on_update")
    private Boolean scmDeleteOnUpdate;

    @JsonProperty("credential")
    private String credential;

    @JsonProperty("timeout")
    private Long timeout;

    @JsonProperty("scm_revision")
    private String scmRevision;

    @JsonProperty("last_job_run")
    private String lastJobRun;

    @JsonProperty("last_job_failed")
    private Boolean hasLastJobFailed;

    @JsonProperty("next_job_run")
    private String nextJobRun;

    @JsonProperty("status")
    private String status;

    @JsonProperty("organization")
    private Long organizationId;

    @JsonProperty("scm_update_on_launch")
    private Boolean scmUpdateOnLaunch;

    @JsonProperty("scm_update_cache_timeout")
    private Long scmUpdateCacheTimeout;

    @JsonProperty("allow_override")
    private Boolean allowOverride;

    @JsonProperty("custom_virtualenv")
    private String customVirtualEnv;

    @JsonProperty("last_update_failed")
    private Boolean hasLastUpdateFailed;

    @JsonProperty("last_updated")
    private String lastUpdated;
}
