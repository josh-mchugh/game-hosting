package com.example.demo.awx.organization.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.organization.feign.model.OrganizationApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class OrganizationFeignServiceTest {

    private IOrganizationClient organizationClient;

    @BeforeEach
    public void setup() {

        organizationClient = Mockito.mock(IOrganizationClient.class);
    }

    @Test
    public void whenClientReturnsListResponse() {

        List<OrganizationApi> expectedApis = Arrays.asList(new OrganizationApi(), new OrganizationApi());

        ListResponse<OrganizationApi> listResponse = new ListResponse<>();
        listResponse.setResults(expectedApis);

        Mockito.when(organizationClient.getOrganizations()).thenReturn(listResponse);

        OrganizationFeignService feignService = new OrganizationFeignService(organizationClient);

        Assertions.assertEquals(expectedApis, feignService.getOrganizations().getResults());
    }
}
