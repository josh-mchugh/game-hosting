package com.example.demo.framework.seed.awx.organization;

import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.awx.organization.feign.OrganizationFeignService;
import com.example.demo.awx.organization.feign.model.OrganizationApi;
import com.example.demo.awx.organization.service.AwxOrganizationService;
import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.SeedService;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationQuery;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AwxOrganizationSeedService implements SeedService<AwxOrganization> {

    private final AwxConfig awxConfig;
    private final OrganizationFeignService organizationFeignService;
    private final QueryGateway queryGateway;
    private final AwxOrganizationService awxOrganizationService;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyAwxOrganizationQuery query = new ExistsAnyAwxOrganizationQuery();
        ExistsAnyAwxOrganizationResponse response = queryGateway.query(query, ExistsAnyAwxOrganizationResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<AwxOrganization> initializeData() {

        Optional<OrganizationApi> organizationApi = organizationFeignService.getOrganizations().getResults().stream()
                .filter(organization -> organization.getId().equals(awxConfig.getOrganization().getId()))
                .findFirst();

        return organizationApi
                .map(this::awxOrganizationCreateRequest)
                .map(awxOrganizationService::handleCreate)
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

    private AwxOrganizationCreateRequest awxOrganizationCreateRequest(OrganizationApi organization) {

        return AwxOrganizationCreateRequest.builder()
                .awxId(organization.getId())
                .name(organization.getName())
                .description(organization.getDescription())
                .build();
    }
}
