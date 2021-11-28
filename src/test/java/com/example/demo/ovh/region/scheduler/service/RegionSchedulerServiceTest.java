package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.feign.RegionFeignService;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameResponse;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameResponse;
import com.example.demo.ovh.region.scheduler.projection.projection.RegionProjection;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.collect.ImmutableList;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionSchedulerServiceTest {

    @Autowired
    private RegionSchedulerService regionSchedulerService;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private QueryGateway queryGateway;

    @MockBean
    private RegionFeignService regionFeignService;

    @Test
    public void testGetRegionNames() {

        List<String> names = Arrays.asList("get-region-names-1", "get-region-names-2");
        Mockito.when(regionFeignService.getRegions()).thenReturn(names);

        List<String> regionNames = regionSchedulerService.getRegionNames();

        Assertions.assertTrue(regionNames.containsAll(names));
    }

    @Test
    public void testProcessedCreatedRegions() throws ExecutionException, InterruptedException {

        RegionApi regionApi = new RegionApi();
        regionApi.setName("processed-create-regions");
        regionApi.setStatus(RegionStatus.UP);

        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionApi);

        Mockito.when(queryGateway.query(new ExistsRegionByNameQuery("not-existing-region"), ExistsRegionByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsRegionByNameResponse(false)));

        ProcessRegionResponse processedResponse = regionSchedulerService.processRegions(ImmutableList.of("not-existing-region"));

        Assertions.assertEquals(1, processedResponse.getCreatedRegions().size());
    }

    @Test
    public void whenRegionSchedulerHasUpdatesThenExpectPopulatedList() throws ExecutionException, InterruptedException {

        RegionApi regionApi = regionApi(RegionStatus.DOWN);

        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionApi);

        Mockito.when(queryGateway.query(new ExistsRegionByNameQuery(regionApi.getName()), ExistsRegionByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsRegionByNameResponse(true)));

        Mockito.when(queryGateway.query(new FetchRegionByNameQuery(regionApi.getName()), FetchRegionByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchRegionByNameResponse(region())));

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessRegionResponse processResponse = regionSchedulerService.processRegions(ImmutableList.of("US-EAST-VA-1"));

        Assertions.assertEquals(1, processResponse.getUpdatedRegions().size());
    }

    @Test
    public void whenRegionSchedulerHasNoUpdatesThenExpectEmptyList() throws ExecutionException, InterruptedException {

        RegionApi regionApi = regionApi(RegionStatus.UP);

        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionApi);

        Mockito.when(queryGateway.query(new ExistsRegionByNameQuery(regionApi.getName()), ExistsRegionByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsRegionByNameResponse(true)));

        Mockito.when(queryGateway.query(new FetchRegionByNameQuery(regionApi.getName()), FetchRegionByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchRegionByNameResponse(region())));

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessRegionResponse processResponse = regionSchedulerService.processRegions(ImmutableList.of("US-EAST-VA-1"));

        Assertions.assertEquals(0, processResponse.getUpdatedRegions().size());
    }

    private RegionApi regionApi(RegionStatus status) {

        RegionApi regionApi = new RegionApi();
        regionApi.setName("US-EAST-VA-1");
        regionApi.setContinentCode("US");
        regionApi.setIpCountries(Arrays.asList("us", "ca"));
        regionApi.setDataCenterLocation("US-EAST-VA");
        regionApi.setStatus(status);

        return regionApi;
    }

    private RegionProjection region() {

        return new RegionProjection(UUID.randomUUID().toString(), "US", "us,ca", "US-EAST-VA", RegionStatus.UP);
    }
}
