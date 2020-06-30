package com.example.demo.web.ansible.model.inventories;

import com.example.demo.web.ansible.model.base.AbstractBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventory extends AbstractBase {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("organization")
    private Long organizationId;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("host_filter")
    private String hostFilter;

    @JsonProperty("variables")
    private String variables;

    @JsonProperty("has_active_failures")
    private Boolean hasActiveFailures;

    @JsonProperty("total_hosts")
    private Integer totalHosts;

    @JsonProperty("hosts_with_active_failures")
    private Integer hostsWithActiveFailures;

    @JsonProperty("total_groups")
    private Integer totalGroups;

    @JsonProperty("has_inventory_sources")
    private Boolean hasInventorySources;

    @JsonProperty("total_inventory_sources")
    private Integer totalInventorySources;

    @JsonProperty("inventory_sources_with_failures")
    private Integer inventorySourcesWithFailures;

    @JsonProperty("insights_credential")
    private String insightCredential;

    @JsonProperty("pending_deletion")
    private Boolean pendingDeletion;
}
