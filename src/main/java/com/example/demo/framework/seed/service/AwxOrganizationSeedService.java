package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.organization.OrganizationClient;
import com.example.demo.awx.feign.organization.model.OrganizationApi;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.awx.organization.service.IAwxOrganizationService;
import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AwxOrganizationSeedService implements ISeedService<AwxOrganization> {

    private final AwxConfig awxConfig;
    private final IAwxOrganizationService awxOrganizationService;
    private final OrganizationClient organizationClient;

    @Override
    public boolean dataNotExists() {

        return !awxOrganizationService.existsAny();
    }

    @Override
    public ImmutableList<AwxOrganization> initializeData() {

        Optional<OrganizationApi> organizationApi = organizationClient.getOrganizations().getResults().stream()
                .filter(organization -> organization.getId().equals(awxConfig.getOrganization().getId()))
                .findFirst();

        return organizationApi
                .map(this::buildAwxCreateRequest)
                .map(awxOrganizationService::handleOrganizationCreate)
                .map(ImmutableList::of)
                .orElseThrow(() -> new RuntimeException("Unable to find AWX Organization, verify it exists with the supplied configuration name"));
    }

    @Override
    public String type() {

        return "Awx Organization";
    }

    @Override
    public Integer order() {

        return 7;
    }

    private AwxOrganizationCreateRequest buildAwxCreateRequest(OrganizationApi organization) {

        return AwxOrganizationCreateRequest.builder()
                .organizationId(organization.getId())
                .name(organization.getName())
                .description(organization.getDescription())
                .build();
    }
}
