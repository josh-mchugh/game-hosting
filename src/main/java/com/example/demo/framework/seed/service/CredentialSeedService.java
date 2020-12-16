package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.credential.aggregate.command.CredentialCreateCommand;
import com.example.demo.ovh.credential.feign.ISshKeyFeignService;
import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import com.example.demo.ovh.credential.feign.model.SshKeyCreateApi;
import com.example.demo.ovh.credential.projector.ICredentialProjector;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CredentialSeedService implements ISeedService<Object> {

    private final OvhConfig ovhConfig;
    private final ISshKeyFeignService sshKeyFeignService;
    private final ICredentialProjector credentialProjector;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !credentialProjector.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        return createSshKeys();
    }

    @Override
    public String type() {

        return "Credential";
    }

    @Override
    public Integer order() {

        return 6;
    }

    private ImmutableList<Object> createSshKeys() {

        List<UUID> credentials = new ArrayList<>();

        List<SshKeyApi> apiResponses = sshKeyFeignService.getSshKeys();

        for(OvhConfig.SshKeyConfig config : ovhConfig.getSshKeyConfigs()) {

            Optional<SshKeyApi> apiResponse = apiResponses.stream()
                    .filter(response -> response.getPublicKey().equals(config.getPublicKey()))
                    .findFirst();

            if(apiResponse.isPresent()) {

                credentials.add(createCredential(config, apiResponse.get()));

            } else {

                credentials.add(createCredential(config, createSshKeyResponse(config)));
            }
        }

        return ImmutableList.copyOf(credentials);
    }

    private SshKeyApi createSshKeyResponse(OvhConfig.SshKeyConfig config) {

        SshKeyCreateApi apiRequest = SshKeyCreateApi.builder()
                .name(config.getName())
                .publicKey(config.getPublicKey())
                .build();

        return sshKeyFeignService.createSshKey(apiRequest);
    }

    private UUID createCredential(OvhConfig.SshKeyConfig config, SshKeyApi apiResponse) {

        CredentialCreateCommand command = CredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .ovhId(apiResponse.getId())
                .name(apiResponse.getName())
                .publicKey(apiResponse.getPublicKey())
                .type(config.getType())
                .build();

        return commandGateway.sendAndWait(command);
    }
}
