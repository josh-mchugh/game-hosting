package com.example.demo.framework.seed.awx.credential;

import com.example.demo.awx.credential.aggregate.command.AwxCredentialCreateCommand;
import com.example.demo.awx.credential.feign.IAwxCredentialFeignService;
import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.credential.feign.model.AwxCredentialCreateApi;
import com.example.demo.awx.organization.projection.IAwxOrganizationProjection;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AwxCredentialSeedService implements ISeedService<Object> {

    private final AwxConfig awxConfig;
    private final IAwxCredentialFeignService credentialFeignService;
    private final IAwxOrganizationProjection awxOrganizationProjection;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyAwxCredentialQuery query = new ExistsAnyAwxCredentialQuery();
        ExistsAnyAwxCredentialResponse response = queryGateway.query(query, ExistsAnyAwxCredentialResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        List<Object> awxCredentials = new ArrayList<>();

        List<AwxConfig.Credential> credentials = awxConfig.getCredentials();
        List<AwxCredentialApi> credentialApis = credentialFeignService.getCredentials().getResults();
        UUID organizationId = getOrganizationId();

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

    private Object createAwxCredential(AwxCredentialApi credentialApi, AwxConfig.Credential credential, UUID organizationId) {

        AwxCredentialCreateCommand command = AwxCredentialCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(organizationId)
                .awxId(credentialApi.getId())
                .name(credentialApi.getName())
                .description(credentialApi.getDescription())
                .privateKey(credential.getPrivateKey().replace("\\n", "\n"))
                .passphrase(credential.getPassphrase())
                .type(credential.getType())
                .build();

        return commandGateway.sendAndWait(command);
    }

    private UUID getOrganizationId() {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(awxConfig.getOrganization().getId());
        FetchAwxOrganizationIdByAwxIdResponse response = awxOrganizationProjection.fetchAwxOrganizationIdByAwxId(query);

        return response.getId();
    }
}
