package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.ovh.region.feign.IRegionFeignService;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.collect.ImmutableList;
import org.axonframework.commandhandling.gateway.CommandGateway;
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

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionSchedulerServiceTest {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IRegionSchedulerService regionSchedulerService;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private IRegionFeignService regionFeignService;

    @Test
    public void testGetRegionNames() {

        List<String> names = Arrays.asList("get-region-names-1", "get-region-names-2");
        Mockito.when(regionFeignService.getRegions()).thenReturn(names);

        List<String> regionNames = regionSchedulerService.getRegionNames();

        Assertions.assertTrue(regionNames.containsAll(names));
    }

    @Test
    public void testProcessedCreatedRegions() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setName("processed-create-regions");
        regionResponse.setStatus(RegionStatus.UP);

        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionResponse);

        ProcessRegionResponse processedResponse = regionSchedulerService.processRegions(ImmutableList.of("not-existing-region"));

        Assertions.assertEquals(1, processedResponse.getCreatedRegions().size());
    }

    @Test
    public void whenRegionSchedulerHasUpdatesThenExpectPopulatedList() {

        RegionCreatedEvent event = regionCreatedEvent();
        regionService.handleCreated(event);

        RegionApi regionApi = regionApi();
        regionApi.setStatus(RegionStatus.DOWN);

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());
        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionApi);

        ProcessRegionResponse processResponse = regionSchedulerService.processRegions(ImmutableList.of("US-EAST-VA-1"));

        Assertions.assertEquals(1, processResponse.getUpdatedRegions().size());
    }

    @Test
    public void whenRegionSchedulerHasNoUpdatesThenExpectEmptyList() {

        RegionCreatedEvent event = regionCreatedEvent();
        regionService.handleCreated(event);

        RegionApi regionApi = regionApi();

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());
        Mockito.when(regionFeignService.getRegion(Mockito.anyString())).thenReturn(regionApi);

        ProcessRegionResponse processResponse = regionSchedulerService.processRegions(ImmutableList.of("US-EAST-VA-1"));

        Assertions.assertEquals(0, processResponse.getUpdatedRegions().size());
    }

    private RegionCreatedEvent regionCreatedEvent() {

        return RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us,ca")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();
    }

    private RegionApi regionApi() {

        RegionApi regionApi = new RegionApi();
        regionApi.setName("US-EAST-VA-1");
        regionApi.setContinentCode("US");
        regionApi.setIpCountries(Arrays.asList("us", "ca"));
        regionApi.setDataCenterLocation("US-EAST-VA");
        regionApi.setStatus(RegionStatus.UP);

        return regionApi;
    }
}
