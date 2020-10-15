package com.example.demo.ovh.flavor.scheduler;

import com.example.demo.ovh.feign.flavor.FlavorClient;
import com.example.demo.ovh.feign.flavor.model.FlavorApi;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.scheduler.service.IFlavorSchedulerService;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.util.TestFlavorCreateRequest;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private IFlavorSchedulerService flavorSchedulerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private FlavorClient flavorClient;

    private Region region;

    @BeforeEach
    public void setup() {

        region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();
    }

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

        FlavorCreateRequest flavorCreateRequest = TestFlavorCreateRequest.createDefault();
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
