package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.entity.service.IFlavorService;
import com.example.demo.ovh.flavor.feign.IFlavorFeignService;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
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
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorSchedulerServiceTest {

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private IFlavorSchedulerService flavorSchedulerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private IFlavorFeignService flavorFeignService;

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
        flavorResponse.setId("ovhId");

        Mockito.when(flavorFeignService.getFlavors()).thenReturn(Collections.singletonList(flavorResponse));

        ImmutableList<FlavorApi> flavorResponses = flavorSchedulerService.getFlavorResponses();

        Assertions.assertEquals(1, flavorResponses.size());
        Assertions.assertEquals(flavorResponse.getId(), flavorResponses.get(0).getId());
    }

    @Test
    public void testProcessorFlavorsCreated() {

        FlavorApi flavorResponse = new FlavorApi();
        flavorResponse.setId("ovhId");
        flavorResponse.setRegionName(region.getName());

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(ImmutableList.of(flavorResponse));

        Assertions.assertEquals(1, CollectionUtils.size(response.getCreatedFlavors()));
        Assertions.assertEquals(0, CollectionUtils.size(response.getUpdatedFlavors()));
    }

    @Test
    public void testProcessorFlavorsUpdated() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("d23f7fd6-a250-4600-bb95-bb4cd12d9a01")
                .regionId(region.getId())
                .name("s1-8")
                .type("ovh.vps-ssd")
                .available(true)
                .hourly("s1-8.consumption")
                .monthly("s1-8.monthly")
                .quota(3)
                .osType("linux")
                .vcpus(2)
                .ram(8000)
                .disk(40)
                .outboundBandwidth(100)
                .inboundBandwidth(100)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        FlavorApi flavorResponse = new FlavorApi();
        flavorResponse.setId(flavor.getOvhId());
        flavorResponse.setRegionName(region.getName());

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(ImmutableList.of(flavorResponse));

        Assertions.assertEquals(0, CollectionUtils.size(response.getCreatedFlavors()));
        Assertions.assertEquals(1, CollectionUtils.size(response.getUpdatedFlavors()));
    }
}
