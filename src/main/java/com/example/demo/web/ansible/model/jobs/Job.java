package com.example.demo.web.ansible.model.jobs;

import com.example.demo.web.ansible.model.base.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job extends AbstractBase {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("unified_job_template")
    private Long unifiedJobTemplateId;

    @JsonProperty("launch_type")
    private String launchType;

    @JsonProperty("status")
    private String status;

    @JsonProperty("failed")
    private Boolean failed;

    @JsonProperty("started")
    private String started;

    @JsonProperty("finished")
    private String finished;

    @JsonProperty("canceled_on")
    private String canceledOn;

    @JsonProperty("elapsed")
    private Double elapsed;

    @JsonProperty("job_explanation")
    private String jobExplanation;

    @JsonProperty("execution_node")
    private String executionNode;

    @JsonProperty("controller_node")
    private String controllerNode;

    @JsonProperty("job_type")
    private String jobType;

    @JsonProperty("inventory")
    private Long inventoryId;

    @JsonProperty("project")
    private Long projectId;

    @JsonProperty("playbook")
    private String playbook;

    @JsonProperty("scm_branch")
    private String scmBranch;

    @JsonProperty("forks")
    private Integer forks;

    @JsonProperty("limit")
    private String limit;

    @JsonProperty("verbosity")
    private Integer verbosity;

    @JsonProperty("extra_vars")
    private String extraVars;

    @JsonProperty("job_tags")
    private String jobTags;

    @JsonProperty("force_handlers")
    private Boolean forceHandlers;

    @JsonProperty("skip_tags")
    private String skipTags;

    @JsonProperty("start_at_task")
    private String startAtTask;

    @JsonProperty("timeout")
    private Long timeout;

    @JsonProperty("use_fact_cache")
    private Boolean useFactCache;

    @JsonProperty("organization")
    private Long organizationId;

    @JsonProperty("job_template")
    private Long jobTemplateId;

    @JsonProperty("passwords_needed_to_start")
    private List<String> passwordNeededToStart;

    @JsonProperty("allow_simultaneous")
    private Boolean allowSimultaneous;

    @JsonProperty("artifacts")
    private Object artifacts;

    @JsonProperty("scm_revision")
    private String scmRevision;

    @JsonProperty("instance_group")
    private String instanceGroup;

    @JsonProperty("diff_mode")
    private Boolean diffMode;

    @JsonProperty("job_slice_number")
    private Integer jobSliceNumber;

    @JsonProperty("job_slice_count")
    private Integer jobSliceCount;

    @JsonProperty("webhook_service")
    private String webhookService;

    @JsonProperty("webhook_credential")
    private String webhookCredential;

    @JsonProperty("webhook_guid")
    private String webhookGuid;
}
