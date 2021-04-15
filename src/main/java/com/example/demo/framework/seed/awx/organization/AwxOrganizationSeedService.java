package com.example.demo.framework.seed.awx.organization;

import com.example.demo.awx.organization.aggregate.command.AwxOrganizationCreateCommand;
import com.example.demo.awx.organization.feign.IOrganizationFeignService;
import com.example.demo.awx.organization.feign.model.OrganizationApi;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationQuery;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AwxOrganizationSeedService implements ISeedService<Object> {

    private final AwxConfig awxConfig;
    private final IOrganizationFeignService organizationFeignService;
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyAwxOrganizationQuery query = new ExistsAnyAwxOrganizationQuery();
        ExistsAnyAwxOrganizationResponse response = queryGateway.query(query, ExistsAnyAwxOrganizationResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        Optional<OrganizationApi> organizationApi = organizationFeignService.getOrganizations().getResults().stream()
                .filter(organization -> organization.getId().equals(awxConfig.getOrganization().getId()))
                .findFirst();

        return organizationApi
                .map(this::awxOrganizationCreateCommand)
                .map(commandGateway::sendAndWait)
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

    private AwxOrganizationCreateCommand awxOrganizationCreateCommand(OrganizationApi organization) {

        return AwxOrganizationCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxId(organization.getId())
                .name(organization.getName())
                .description(organization.getDescription())
                .build();
    }
}
