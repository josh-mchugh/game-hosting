package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.example.demo.ovh.feign.ssh.SshKeyClient;
import com.example.demo.ovh.feign.ssh.model.SshKeyApi;
import com.example.demo.ovh.feign.ssh.model.SshKeyCreateApi;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CredentialSeedService implements ISeedService<Credential> {

    private final OvhConfig ovhConfig;
    private final ICredentialService credentialService;
    private final SshKeyClient sshKeyClient;

    @Override
    public boolean dataNotExists() {

        return !credentialService.existsAny();
    }

    @Override
    public ImmutableList<Credential> initializeData() {

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

    private SshKeyApi createSshKeyResponse(OvhConfig.SshKeyConfig config) {

        SshKeyCreateApi apiRequest = SshKeyCreateApi.builder()
                .name(config.getName())
                .publicKey(config.getPublicKey())
                .build();

        return sshKeyClient.createSshKey(ovhConfig.getProjectId(), apiRequest);
    }

    private Credential createCredential(OvhConfig.SshKeyConfig config, SshKeyApi apiResponse) {

        CredentialCreateRequest request = CredentialCreateRequest.builder()
                .sshKeyId(apiResponse.getId())
                .name(apiResponse.getName())
                .publicKey(apiResponse.getPublicKey())
                .privateKey(config.getPrivateKey())
                .type(config.getType())
                .build();

        return credentialService.handleSshKeyCreate(request);
    }

    private ImmutableList<Credential> createSshKeys() {

        List<Credential> credentials = new ArrayList<>();

        List<SshKeyApi> apiResponses = sshKeyClient.getSshKeys(ovhConfig.getProjectId());

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
}
