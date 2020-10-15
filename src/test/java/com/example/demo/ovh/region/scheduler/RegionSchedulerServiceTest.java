package com.example.demo.ovh.region.scheduler;

import com.example.demo.ovh.feign.region.RegionClient;
import com.example.demo.ovh.feign.region.model.RegionApi;
import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.ovh.region.scheduler.service.IRegionSchedulerService;
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
    private RegionClient regionClient;

    @Test
    public void testGetRegionNames() {

        List<String> names = Arrays.asList("get-region-names-1", "get-region-names-2");
        Mockito.when(regionClient.getRegions(Mockito.anyString())).thenReturn(names);

        List<String> regionNames = regionSchedulerService.getRegionNames();

        Assertions.assertEquals(2, regionNames.size());
        Assertions.assertTrue(regionNames.containsAll(names));
    }

    @Test
    public void testProcessedUpdateRegionsExisting() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        RegionApi regionResponse = new RegionApi();
        regionResponse.setName(region.getName());
        regionResponse.setStatus(RegionStatus.DOWN);

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());
        Mockito.when(regionClient.getRegion(Mockito.anyString(), Mockito.anyString())).thenReturn(regionResponse);

        ProcessRegionResponse processResponse = regionSchedulerService.processRegions(ImmutableList.of(region.getName()));

        Assertions.assertEquals(1, processResponse.getUpdatedRegions().size());
        Assertions.assertEquals(0, processResponse.getCreatedRegions().size());
    }

    @Test
    public void testProcessedCreatedRegions() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setName("processed-create-regions");
        regionResponse.setIpCountries(Arrays.asList("uk", "us", "ca"));
        regionResponse.setStatus(RegionStatus.UP);

        Mockito.when(regionClient.getRegion(Mockito.anyString(), Mockito.anyString())).thenReturn(regionResponse);

        ProcessRegionResponse processedResponse = regionSchedulerService.processRegions(ImmutableList.of("not-existing-region"));

        Assertions.assertEquals(1, processedResponse.getCreatedRegions().size());
        Assertions.assertEquals(0, processedResponse.getUpdatedRegions().size());
    }
}
