package com.example.demo.framework.seed.awx.credential;

import com.example.demo.awx.credential.feign.AwxCredentialFeignService;
import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.credential.feign.model.AwxCredentialCreateApi;
import com.example.demo.awx.credential.service.AwxCredentialService;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.SeedService;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialResponse;
import com.example.demo.framework.seed.awx.credential.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AwxCredentialSeedService implements SeedService<Object> {

    private final AwxConfig awxConfig;
    private final AwxCredentialFeignService credentialFeignService;
    private final QueryGateway queryGateway;
    private final AwxCredentialService awxCredentialService;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyAwxCredentialQuery query = new ExistsAnyAwxCredentialQuery();
        ExistsAnyAwxCredentialResponse response = queryGateway.query(query, ExistsAnyAwxCredentialResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() throws ExecutionException, InterruptedException {

        ImmutableList.Builder<Object> awxCredentials = ImmutableList.builder();

        List<AwxConfig.Credential> credentials = awxConfig.getCredentials();
        List<AwxCredentialApi> credentialApis = credentialFeignService.getCredentials().getResults();
        String organizationId = getOrganizationId();

        for(AwxConfig.Credential credential : credentials) {

            Optional<AwxCredentialApi> credentialApi = credentialApis.stream()
                    .filter(api -> api.getName().equals(credential.getName()))
                    .findFirst();

            if (credentialApi.isPresent()) {

                awxCredentials.add(createAwxCredential(credentialApi.get(), credential, organizationId));

            } else {

                awxCredentials.add(createAwxCredential(createCredentialApi(credential), credential, organizationId));
            }
        }

        return awxCredentials.build();
    }

    @Override
    public String type() {

        return "Awx Credential";
    }

    @Override
    public Integer order() {

        return 8;
    }

    private AwxCredentialCreateApi.Input createCredentialApiInputs(AwxConfig.Credential credential) {

        return AwxCredentialCreateApi.Input.builder()
                .privateKey(credential.getPrivateKey().replace("\\n", "\n"))
                .passphrase(credential.getPassphrase())
                .build();
    }

    private AwxCredentialCreateApi createCredentialApiRequest(AwxConfig.Credential credential) {

        return AwxCredentialCreateApi.builder()
                .name(credential.getName())
                .credentialType(credential.getType().getId())
                .inputs(createCredentialApiInputs(credential))
                .build();
    }

    private AwxCredentialApi createCredentialApi(AwxConfig.Credential credential) {

        return credentialFeignService.createCredential(createCredentialApiRequest(credential));
    }

    private Object createAwxCredential(AwxCredentialApi credentialApi, AwxConfig.Credential credential, String organizationId) {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(organizationId)
                .awxId(credentialApi.getId())
                .name(credentialApi.getName())
                .description(credentialApi.getDescription())
                .privateKey(credential.getPrivateKey().replace("\\n", "\n"))
                .passphrase(credential.getPassphrase())
                .type(credential.getType())
                .build();

        return awxCredentialService.handleCreated(request);
    }

    private String getOrganizationId() throws ExecutionException, InterruptedException {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(awxConfig.getOrganization().getId());
        FetchAwxOrganizationIdByAwxIdResponse response = queryGateway.query(query, FetchAwxOrganizationIdByAwxIdResponse.class).get();

        return response.getId();
    }
}
