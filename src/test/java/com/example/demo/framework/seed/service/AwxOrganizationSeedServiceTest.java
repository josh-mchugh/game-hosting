package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.organization.OrganizationClient;
import com.example.demo.awx.feign.organization.model.OrganizationApi;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxOrganizationSeedServiceTest {

    @Autowired
    private AwxOrganizationSeedService awxOrganizationSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private OrganizationClient organizationClient;

    @Test
    public void whenAwxOrganizationExistsThenDataNotExistsReturnsFalse() {

        sampleBuilder.builder().awxOrganization().build();

        Assertions.assertFalse(awxOrganizationSeedService.dataNotExists());
    }

    @Test
    public void whenAwxOrganizationDoesNotExistsThenDataNotExistsReturnsTrue() {

        Assertions.assertTrue(awxOrganizationSeedService.dataNotExists());
    }

    @Test
    public void whenOrganizationClientReturnsEmptyArrayThenThrowException() {

        Mockito.when(organizationClient.getOrganizations()).thenReturn(new ListResponse<>());

        Assertions.assertThrows(RuntimeException.class, () -> awxOrganizationSeedService.initializeData());
    }

    @Test
    public void whenOrganizationClientReturnsNotMatchingConfigThenThrowException() {

        OrganizationApi organizationApi = new OrganizationApi();
        organizationApi.setId(1L);
        organizationApi.setName("name");
        organizationApi.setDescription("description");

        ListResponse<OrganizationApi> clientResponse = new ListResponse<>();
        clientResponse.setResults(Collections.singletonList(organizationApi));

        Mockito.when(organizationClient.getOrganizations()).thenReturn(clientResponse);

        Assertions.assertThrows(RuntimeException.class, () -> awxOrganizationSeedService.initializeData());
    }

    @Test
    public void whenOrganizationClientReturnsMatchingConfigThenReturnList() {

        OrganizationApi organizationApi = new OrganizationApi();
        organizationApi.setId(2L);
        organizationApi.setName("Game Hosting Service");

        ListResponse<OrganizationApi> clientResponse = new ListResponse<>();
        clientResponse.setResults(Collections.singletonList(organizationApi));

        Mockito.when(organizationClient.getOrganizations()).thenReturn(clientResponse);

        ImmutableList<Object> organizations = awxOrganizationSeedService.initializeData();

        Assertions.assertEquals(1, organizations.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Awx Organization", awxOrganizationSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(awxOrganizationSeedService.type());
    }

    @Test
    public void whenOrderHasValueReturnValue() {

        Assertions.assertEquals(7, awxOrganizationSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(awxOrganizationSeedService.order());
    }
}
