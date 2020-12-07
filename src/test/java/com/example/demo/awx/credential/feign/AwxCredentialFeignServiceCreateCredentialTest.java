package com.example.demo.awx.credential.feign;

import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.credential.feign.model.AwxCredentialCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AwxCredentialFeignServiceCreateCredentialTest {

    private AwxConfig awxConfig;
    private IAwxCredentialClient awxCredentialClient;

    @BeforeEach
    public void setup() {

        AwxConfig.Organization organization = new AwxConfig.Organization();
        organization.setId(1L);

        awxConfig = new AwxConfig();
        awxConfig.setOrganization(organization);

        awxCredentialClient = Mockito.mock(IAwxCredentialClient.class);
    }

    @Test
    public void whenGetCredentialsThenReturnCredentials() {

        AwxCredentialApi awxCredentialApi = new AwxCredentialApi();
        Mockito.when(awxCredentialClient.createCredential(Mockito.anyLong(), Mockito.any())).thenReturn(awxCredentialApi);

        AwxCredentialCreateApi awxCredentialCreateApi = AwxCredentialCreateApi.builder()
                .name("name")
                .build();

        AwxCredentialFeignService awxCredentialFeignService = new AwxCredentialFeignService(awxConfig, awxCredentialClient);

        AwxCredentialApi response = awxCredentialFeignService.createCredential(awxCredentialCreateApi);

        Assertions.assertEquals(awxCredentialApi, response);
    }
}
