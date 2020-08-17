package com.example.demo.ovh.flavor;

import com.example.demo.ovh.feign.flavor.FlavorClient;
import com.example.demo.ovh.feign.flavor.model.FlavorApi;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.scheduler.service.IFlavorSchedulerService;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.sample.TestFlavorUtil;
import com.example.demo.sample.TestRegionUtil;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
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
public class FlavorSchedulerTest {

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IFlavorSchedulerService flavorSchedulerService;

    @MockBean
    private FlavorClient flavorClient;

    @Test
    public void testGetFlavorResponses() {

        FlavorApi flavorResponse = new FlavorApi();
        flavorResponse.setFlavorId("get-flavor-responses");

        Mockito.when(flavorClient.getFlavors(Mockito.anyString())).thenReturn(Collections.singletonList(flavorResponse));

        ImmutableList<FlavorApi> flavorResponses = flavorSchedulerService.getFlavorResponses();

        Assertions.assertEquals(1, flavorResponses.size());
        Assertions.assertEquals(flavorResponse.getFlavorId(), flavorResponses.get(0).getFlavorId());
    }

    @Test
    public void testProcessorFlavorsCreated() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("processor-flavors-created");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorApi flavorResponse = new FlavorApi();
        flavorResponse.setFlavorId("processor-flavors-created");
        flavorResponse.setRegionName(region.getName());

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(ImmutableList.of(flavorResponse));

        Assertions.assertEquals(1, CollectionUtils.size(response.getCreatedFlavors()));
        Assertions.assertEquals(0, CollectionUtils.size(response.getUpdatedFlavors()));
        Assertions.assertNotNull(response.getCreatedFlavors().get(0).getId());
        Assertions.assertEquals(flavorResponse.getFlavorId(), response.getCreatedFlavors().get(0).getFlavorId());
    }

    @Test
    public void testProcessorFlavorsUpdated() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("processor-flavors-updated");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorCreateRequest flavorCreateRequest = TestFlavorUtil.createFlavor("processor-flavors-updated", region.getName());
        Flavor flavor = flavorService.handleFlavorCreate(flavorCreateRequest);

        FlavorApi flavorResponse = new FlavorApi();
        flavorResponse.setFlavorId(flavor.getFlavorId());
        flavorResponse.setRegionName(region.getName());

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(ImmutableList.of(flavorResponse));

        Assertions.assertEquals(0, CollectionUtils.size(response.getCreatedFlavors()));
        Assertions.assertEquals(1, CollectionUtils.size(response.getUpdatedFlavors()));
        Assertions.assertEquals(flavor.getId(), response.getUpdatedFlavors().get(0).getId());
    }
}
