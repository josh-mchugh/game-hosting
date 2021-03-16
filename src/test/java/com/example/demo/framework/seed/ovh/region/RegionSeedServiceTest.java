package com.example.demo.framework.seed.ovh.region;

import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionQuery;
import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionResponse;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.feign.IRegionFeignService;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionSeedServiceTest {

    @Autowired
    private RegionSeedService regionSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    public IRegionFeignService regionFeignService;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenRegionExistsThenDoesNotExistsReturnsFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyRegionQuery(), ExistsAnyRegionResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyRegionResponse(true)));

        Assertions.assertFalse(regionSeedService.dataNotExists());
    }

    @Test
    public void whenRegionDoesNotExistsThenDoesNotExistsReturnsTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyRegionQuery(), ExistsAnyRegionResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyRegionResponse(false)));

        Assertions.assertTrue(regionSeedService.dataNotExists());
    }

    @Test
    public void whenRegionsApiReturnsEmptyArrayThenReturnEmptyArray() {

        Mockito.when(regionFeignService.getRegions()).thenReturn(Collections.emptyList());

        ImmutableList<Object> regions = regionSeedService.initializeData();

        Assertions.assertEquals(0, regions.size());
    }

    @Test
    public void whenRegionApiReturnsIsValidThenReturnArrayList() {

        Mockito.when(regionFeignService.getRegions()).thenReturn(Collections.singletonList("us-east"));

        RegionApi regionApi = new RegionApi();
        regionApi.setName("name");
        regionApi.setStatus(RegionStatus.UP);
        regionApi.setIpCountries(Arrays.asList("uk", "us", "ca"));

        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionApi);

        ImmutableList<Object> regions = regionSeedService.initializeData();

        Assertions.assertEquals(1, regions.size());
    }

    @Test
    public void whenRegionApiReturnsIsValidWithNullIpCountriesThenReturnArrayList() {

        Mockito.when(regionFeignService.getRegions()).thenReturn(Collections.singletonList("us-east"));

        RegionApi regionApi = new RegionApi();
        regionApi.setName("name");
        regionApi.setStatus(RegionStatus.UP);
        regionApi.setIpCountries(null);

        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionApi);

        ImmutableList<Object> regions = regionSeedService.initializeData();

        Assertions.assertEquals(1, regions.size());
    }

    @Test
    public void whenRegionsApiThrowsErrorThenThrowError() {

        Mockito.when(regionFeignService.getRegions()).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> regionSeedService.initializeData());
    }

    @Test
    public void whenRegionApiThrowsErrorThenThrowError() {

        Mockito.when(regionFeignService.getRegions()).thenReturn(Collections.singletonList("us-east"));

        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> regionSeedService.initializeData());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Region", regionSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(regionSeedService.type());
    }

    @Test
    public void whenOrderHasValueThenReturnValue() {

        Assertions.assertEquals(2, regionSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(regionSeedService.order());
    }
}
