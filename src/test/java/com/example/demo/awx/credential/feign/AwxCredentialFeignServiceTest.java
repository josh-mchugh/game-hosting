package com.example.demo.awx.credential.feign;

import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.feign.ListResponse;
import com.example.demo.framework.properties.AwxConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class AwxCredentialFeignServiceTest {

    private AwxConfig awxConfig;
    private IAwxCredentialClient awxCredentialClient;
    
    private IAwxCredentialFeignService awxCredentialFeignService;

    @BeforeEach
    public void setup() {

        AwxConfig.Organization organization = new AwxConfig.Organization();
        organization.setId(1L);
        
        awxConfig = new AwxConfig();
        awxConfig.setOrganization(organization);
        
        awxCredentialClient = Mockito.mock(IAwxCredentialClient.class);
    
        awxCredentialFeignService = new AwxCredentialFeignService(awxConfig, awxCredentialClient);
    }

    @Test
    public void whenGetCredentialsThenReturnCredentials() {
        
        List<AwxCredentialApi> credentials = new ArrayList<>();
        credentials.add(new AwxCredentialApi());
        
        ListResponse<AwxCredentialApi> mockResponse = new ListResponse<>();
        mockResponse.setResults(credentials);
        
        Mockito.when(awxCredentialClient.getCredentials(Mockito.anyLong())).thenReturn(mockResponse);
    
        ListResponse<AwxCredentialApi> response = awxCredentialFeignService.getCredentials();

        Assertions.assertEquals(credentials, response.getResults());
    }
}
