package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.ovh.feign.PlanCodeApi;
import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.entity.service.FlavorService;
import com.example.demo.ovh.flavor.feign.FlavorFeignService;
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
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorSchedulerServiceTest {

    @Autowired
    private FlavorService flavorService;

    @Autowired
    private FlavorSchedulerService flavorSchedulerService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private FlavorFeignService flavorFeignService;

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
    }

    @Test
    public void testProcessorFlavorsCreated() throws ExecutionException, InterruptedException {

        FlavorApi flavorResponse = new FlavorApi();
        flavorResponse.setId("ovhId");
        flavorResponse.setRegionName(region.getName());

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(ImmutableList.of(flavorResponse));

        Assertions.assertEquals(1, CollectionUtils.size(response.getCreatedFlavors()));
    }

    @Test
    public void whenFlavorResponseIsNotDifferentThenNoUpdatedFlavors() throws ExecutionException, InterruptedException {

        FlavorCreatedEvent event = flavorCreatedEvent();
        Flavor flavor = flavorService.handleCreated(event);

        FlavorApi flavorResponse = flavorApi(flavor.getOvhId());

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(ImmutableList.of(flavorResponse));

        Assertions.assertEquals(0, CollectionUtils.size(response.getUpdatedFlavors()));
    }

    @Test
    public void whenFlavorResponseIsDifferentThenExpectUpdatedFlavors() throws ExecutionException, InterruptedException {

        FlavorCreatedEvent event = flavorCreatedEvent();
        Flavor flavor = flavorService.handleCreated(event);

        FlavorApi flavorResponse = flavorApi(flavor.getOvhId());
        flavorResponse.setAvailable(false);

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ProcessedFlavorsResponse response = flavorSchedulerService.processFlavors(ImmutableList.of(flavorResponse));

        Assertions.assertEquals(1, CollectionUtils.size(response.getUpdatedFlavors()));
    }

    private FlavorCreatedEvent flavorCreatedEvent() {

        return FlavorCreatedEvent.builder()
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
    }

    private FlavorApi flavorApi(String ovhId) {

        PlanCodeApi planCodes = new PlanCodeApi();
        planCodes.setHourly("s1-8.consumption");
        planCodes.setMonthly("s1-8.monthly");

        FlavorApi flavorResponse = new FlavorApi();
        flavorResponse.setId(ovhId);
        flavorResponse.setRegionName(region.getName());
        flavorResponse.setName("s1-8");
        flavorResponse.setType("ovh.vps-ssd");
        flavorResponse.setAvailable(true);
        flavorResponse.setPlanCodes(planCodes);
        flavorResponse.setQuota(3);
        flavorResponse.setOsType("linux");
        flavorResponse.setVcpus(2);
        flavorResponse.setRam(8000);
        flavorResponse.setDisk(40);
        flavorResponse.setOutboundBandwidth(100);
        flavorResponse.setInboundBandwidth(100);

        return flavorResponse;
    }
}
