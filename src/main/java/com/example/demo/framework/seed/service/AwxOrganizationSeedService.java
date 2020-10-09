package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.organization.OrganizationClient;
import com.example.demo.awx.feign.organization.model.OrganizationApi;
import com.example.demo.awx.organization.aggregate.command.AwxOrganizationCreateCommand;
import com.example.demo.awx.organization.projection.IAwxOrganizationProjection;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AwxOrganizationSeedService implements ISeedService<Object> {

    private final AwxConfig awxConfig;
    private final IAwxOrganizationProjection awxOrganizationProjection;
    private final OrganizationClient organizationClient;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !awxOrganizationProjection.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        Optional<OrganizationApi> organizationApi = organizationClient.getOrganizations().getResults().stream()
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
                .organizationId(organization.getId())
                .name(organization.getName())
                .description(organization.getDescription())
                .build();
    }
}
