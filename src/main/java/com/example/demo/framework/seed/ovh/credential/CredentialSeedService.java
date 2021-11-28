package com.example.demo.framework.seed.ovh.credential;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.SeedService;
import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialQuery;
import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialResponse;
import com.example.demo.ovh.credential.aggregate.command.CredentialCreateCommand;
import com.example.demo.ovh.credential.feign.SshKeyFeignService;
import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import com.example.demo.ovh.credential.feign.model.SshKeyCreateApi;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CredentialSeedService implements SeedService<Object> {

    private final OvhConfig ovhConfig;
    private final SshKeyFeignService sshKeyFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyCredentialQuery query = new ExistsAnyCredentialQuery();
        ExistsAnyCredentialResponse response = queryGateway.query(query, ExistsAnyCredentialResponse.class).get();

        return !response.exists();
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
