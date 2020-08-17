package com.example.demo.ovh.region;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.feign.model.OvhRegionApiResponse;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.scheduler.service.IRegionSchedulerService;
import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.sample.TestRegionUtil;
import com.google.common.collect.ImmutableList;
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

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionSchedulerServiceTest {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IRegionSchedulerService regionSchedulerService;

    @MockBean
    private OvhClient ovhClient;

    @Test
    public void testGetRegionNames() {

        List<String> names = Arrays.asList("get-region-names-1", "get-region-names-2");
        Mockito.when(ovhClient.getRegions(Mockito.anyString())).thenReturn(names);

        List<String> regionNames = regionSchedulerService.getRegionNames();

        Assertions.assertEquals(2, regionNames.size());
        Assertions.assertTrue(regionNames.containsAll(names));
    }

    @Test
    public void testProcessedUpdateRegionsExisting() {

        RegionCreateRequest request = TestRegionUtil.createRegion("processed-update-regions-existing");
        Region region = regionService.handleRegionCreate(request);

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setName(request.getName());
        regionResponse.setStatus(RegionStatus.DOWN);

        Mockito.when(ovhClient.getRegion(Mockito.anyString(), Mockito.anyString())).thenReturn(regionResponse);

        ProcessRegionResponse processResponse = regionSchedulerService.processRegions(ImmutableList.of(region.getName()));
        Region updatedRegion = processResponse.getUpdatedRegions().get(0);

        Assertions.assertEquals(1, processResponse.getUpdatedRegions().size());
        Assertions.assertEquals(0, processResponse.getCreatedRegions().size());

        Assertions.assertEquals(region.getId(), updatedRegion.getId());
        Assertions.assertEquals(region.getName(), updatedRegion.getName());

        Assertions.assertNotEquals(region.getStatus(), updatedRegion.getStatus());
        Assertions.assertEquals(RegionStatus.DOWN, updatedRegion.getStatus());
    }

    @Test
    public void testProcessedCreatedRegions() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setName("processed-create-regions");
        regionResponse.setStatus(RegionStatus.UP);

        Mockito.when(ovhClient.getRegion(Mockito.anyString(), Mockito.anyString())).thenReturn(regionResponse);

        ProcessRegionResponse processedResponse = regionSchedulerService.processRegions(ImmutableList.of("not-existing-region"));
        Region createdRegion = processedResponse.getCreatedRegions().get(0);

        Assertions.assertEquals(1, processedResponse.getCreatedRegions().size());
        Assertions.assertEquals(0, processedResponse.getUpdatedRegions().size());

        Assertions.assertNotNull(createdRegion.getId());
        Assertions.assertEquals(createdRegion.getName(), regionResponse.getName());
        Assertions.assertEquals(RegionStatus.UP, createdRegion.getStatus());
    }
}
