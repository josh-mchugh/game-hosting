package com.example.demo.framework.seed.ovh.flavor;

import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorResponse;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.flavor.feign.FlavorFeignService;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import feign.FeignException;
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
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorSeedServiceTest {

    @Autowired
    private FlavorSeedService flavorSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    public FlavorFeignService flavorFeignService;

    @MockBean
    public QueryGateway queryGateway;

    @Test
    public void whenFlavorsDoNotExistsThenReturnTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyFlavorQuery(), ExistsAnyFlavorResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyFlavorResponse(false)));

        boolean doesNotExists = flavorSeedService.dataNotExists();

        Assertions.assertTrue(doesNotExists);
    }

    @Test
    public void whenFlavorExistsThenReturnFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyFlavorQuery(), ExistsAnyFlavorResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyFlavorResponse(true)));

        boolean doesNotExists = flavorSeedService.dataNotExists();

        Assertions.assertFalse(doesNotExists);
    }

    @Test
    public void whenInitializeDataIsValidThenReturnList() throws ExecutionException, InterruptedException {

        FlavorApi flavorApi = new FlavorApi();
        flavorApi.setId("id");
        flavorApi.setRegionName("regionName");

        FetchRegionIdsGroupedByNameResponse response = new FetchRegionIdsGroupedByNameResponse(ImmutableMap.of("regionName", UUID.randomUUID().toString()));
        Mockito.when(queryGateway.query(new FetchRegionIdsGroupedByNameQuery(), FetchRegionIdsGroupedByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(response));

        Mockito.when(flavorFeignService.getFlavors()).thenReturn(Collections.singletonList(flavorApi));

        ImmutableList<Object> flavors = flavorSeedService.initializeData();

        Assertions.assertEquals(1, flavors.size());
    }

    @Test
    public void whenApiReturnsEmptyListThenReturnEmptyList() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new FetchRegionIdsGroupedByNameQuery(), FetchRegionIdsGroupedByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchRegionIdsGroupedByNameResponse(ImmutableMap.of())));

        Mockito.when(flavorFeignService.getFlavors()).thenReturn(Collections.emptyList());

        ImmutableList<Object> flavors = flavorSeedService.initializeData();

        Assertions.assertEquals(0, flavors.size());
    }

    @Test
    public void whenApiThrowsExceptionThenThrowException() {

        Mockito.when(queryGateway.query(new FetchRegionIdsGroupedByNameQuery(), FetchRegionIdsGroupedByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchRegionIdsGroupedByNameResponse(ImmutableMap.of())));

        Mockito.when(flavorFeignService.getFlavors()).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> flavorSeedService.initializeData());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Flavor", flavorSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(flavorSeedService.type());
    }

    @Test
    public void whenOrderHasValueThenReturnValue() {

        Assertions.assertEquals(3, flavorSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(flavorSeedService.order());
    }
}
