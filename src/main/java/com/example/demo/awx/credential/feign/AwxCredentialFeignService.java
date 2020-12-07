package com.example.demo.awx.credential.feign;

import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.credential.feign.model.AwxCredentialCreateApi;
import com.example.demo.awx.feign.ListResponse;
import com.example.demo.framework.properties.AwxConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxCredentialFeignService implements IAwxCredentialFeignService {

    private final AwxConfig awxConfig;
    private final IAwxCredentialClient credentialClient;

    @Override
    public ListResponse<AwxCredentialApi> getCredentials() {

        return credentialClient.getCredentials(awxConfig.getOrganization().getId());
    }

    @Override
    public AwxCredentialApi createCredential(AwxCredentialCreateApi body) {

        return credentialClient.createCredential(awxConfig.getOrganization().getId(), body);
    }
}
