package com.example.demo.framework.seed.awx.organization;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.organization.feign.OrganizationFeignService;
import com.example.demo.awx.organization.feign.model.OrganizationApi;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationQuery;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationResponse;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxOrganizationSeedServiceTest {

    @Autowired
    private AwxOrganizationSeedService awxOrganizationSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private OrganizationFeignService organizationFeignService;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenAwxOrganizationExistsThenDataNotExistsReturnsFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyAwxOrganizationQuery(), ExistsAnyAwxOrganizationResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyAwxOrganizationResponse(true)));

        Assertions.assertFalse(awxOrganizationSeedService.dataNotExists());
    }

    @Test
    public void whenAwxOrganizationDoesNotExistsThenDataNotExistsReturnsTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyAwxOrganizationQuery(), ExistsAnyAwxOrganizationResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyAwxOrganizationResponse(false)));

        Assertions.assertTrue(awxOrganizationSeedService.dataNotExists());
    }

    @Test
    public void whenOrganizationClientReturnsEmptyArrayThenThrowException() {

        Mockito.when(organizationFeignService.getOrganizations()).thenReturn(new ListResponse<>());

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

        Mockito.when(organizationFeignService.getOrganizations()).thenReturn(clientResponse);

        Assertions.assertThrows(RuntimeException.class, () -> awxOrganizationSeedService.initializeData());
    }

    @Test
    public void whenOrganizationClientReturnsMatchingConfigThenReturnList() {

        OrganizationApi organizationApi = new OrganizationApi();
        organizationApi.setId(3L);
        organizationApi.setName("Game Hosting Service");

        ListResponse<OrganizationApi> clientResponse = new ListResponse<>();
        clientResponse.setResults(Collections.singletonList(organizationApi));

        Mockito.when(organizationFeignService.getOrganizations()).thenReturn(clientResponse);

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
