package com.example.demo.framework.seed.service;

import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.credential.service.IAwxCredentialService;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.feign.credential.CredentialClient;
import com.example.demo.awx.feign.credential.model.CredentialApi;
import com.example.demo.awx.feign.credential.model.CredentialCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AwxCredentialSeedService implements ISeedService<AwxCredential> {

    private final AwxConfig awxConfig;
    private final IAwxCredentialService awxCredentialService;
    private final CredentialClient credentialClient;

    @Override
    public boolean dataNotExists() {

        return !awxCredentialService.existsAny();
    }

    @Override
    public ImmutableList<AwxCredential> initializeData() {

        List<AwxConfig.Credential> credentials = awxConfig.getCredentials();
        List<CredentialApi> credentialApis = credentialClient.getCredentials(awxConfig.getOrganization().getId()).getResults();

        List<AwxCredential> awxCredentials = new ArrayList<>();

        for(AwxConfig.Credential credential : credentials) {

            Optional<CredentialApi> credentialApi = credentialApis.stream()
                    .filter(api -> api.getName().equals(credential.getName()))
                    .findFirst();

            if (credentialApi.isPresent()) {

                awxCredentials.add(createAwxCredential(credentialApi.get(), credential));

            } else {

                awxCredentials.add(createAwxCredential(createCredentialApi(credential), credential));
            }
        }

        return ImmutableList.copyOf(awxCredentials);
    }

    @Override
    public String type() {

        return "Awx Credential";
    }

    @Override
    public Integer order() {

        return 8;
    }

    private AwxCredentialCreateRequest buildCredentialCreateRequest(CredentialApi credentialApi, AwxConfig.Credential credential) {

        return AwxCredentialCreateRequest.builder()
                .credentialId(credentialApi.getId())
                .name(credentialApi.getName())
                .description(credentialApi.getDescription())
                .privateKey(credential.getPrivateKey().replace("\\n", "\n"))
                .passphrase(credential.getPassphrase())
                .organizationId(credentialApi.getOrganizationId())
                .type(credentialApi.getCredentialType())
                .build();
    }

    private CredentialCreateApi.Input createCredentialApiInputs(AwxConfig.Credential credential) {

        return CredentialCreateApi.Input.builder()
                .privateKey(credential.getPrivateKey().replace("\\n", "\n"))
                .passphrase(credential.getPassphrase())
                .build();
    }

    private CredentialCreateApi createCredentialApiRequest(AwxConfig.Credential credential) {

        return CredentialCreateApi.builder()
                .name(credential.getName())
                .credentialType(credential.getType().getId())
                .inputs(createCredentialApiInputs(credential))
                .build();
    }

    private CredentialApi createCredentialApi(AwxConfig.Credential credential) {

        return credentialClient.createCredential(awxConfig.getOrganization().getId(), createCredentialApiRequest(credential));
    }

    private AwxCredential createAwxCredential(CredentialApi credentialApi, AwxConfig.Credential credential) {

        AwxCredentialCreateRequest request = buildCredentialCreateRequest(credentialApi, credential);

        return awxCredentialService.handleAwxCredentialCreate(request);
    }
}
