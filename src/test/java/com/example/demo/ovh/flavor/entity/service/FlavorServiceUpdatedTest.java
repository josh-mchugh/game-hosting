package com.example.demo.ovh.flavor.entity.service;

import com.example.demo.ovh.flavor.aggregate.event.FlavorUpdatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorServiceUpdatedTest {

    @Autowired
    private FlavorService flavorService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private Flavor flavor;

    @BeforeEach
    public void setup() {

        flavor = sampleBuilder.builder()
                .region()
                .flavor()
                .build()
                .getFlavor();
    }

    @Test
    public void whenUpdateIsValidThenReturnExpected() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .name("name")
                .type("type")
                .available(true)
                .hourly("hourly")
                .monthly("monthly")
                .quota(1)
                .osType("osType")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Flavor expected = Flavor.builder()
                .id(flavor.getId())
                .ovhId(flavor.getOvhId())
                .name("name")
                .type("type")
                .available(true)
                .hourly("hourly")
                .monthly("monthly")
                .quota(1)
                .osType("osType")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1)
                .build();

        Assertions.assertEquals(expected, flavor);
    }

    @Test
    public void whenUpdateIsMinValidThenReturnExpected() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Flavor expected = Flavor.builder()
                .id(flavor.getId())
                .ovhId(flavor.getOvhId())
                .build();

        Assertions.assertEquals(expected, flavor);
    }

    @Test
    public void whenUpdateHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> flavorService.handleCreated(null));
    }

    @Test
    public void whenUpdateHasIdThenReturnId() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals(flavor.getId(), flavor.getId());
    }

    @Test
    public void whenUpdateHasNullIdThenThrowException() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder().build();

        Assertions.assertThrows(NullPointerException.class, () -> flavorService.handleUpdated(event));
    }

    @Test
    public void whenUpdateHasInvalidIdThenThrowException() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> flavorService.handleUpdated(event));
    }

    @Test
    public void whenUpdateHasNameThenReturnName() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .name("name")
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals("name", flavor.getName());
    }

    @Test
    public void whenUpdateHasTypeThenReturnType() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .type("type")
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals("type", flavor.getType());
    }

    @Test
    public void whenUpdateHasAvailableThenReturnAvailable() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .available(true)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertTrue(flavor.getAvailable());
    }

    @Test
    public void whenUpdateHasHourlyThenReturnHourly() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .hourly("hourly")
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals("hourly", flavor.getHourly());
    }

    @Test
    public void whenUpdateHasMonthlyThenReturnMonthly() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .monthly("monthly")
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals("monthly", flavor.getMonthly());
    }

    @Test
    public void whenUpdateHasQuotaThenReturnQuota() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .quota(1)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals(1, flavor.getQuota());
    }

    @Test
    public void whenUpdateHasOsTypeThenReturnOsType() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .osType("osType")
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals("osType", flavor.getOsType());
    }

    @Test
    public void whenUpdateHasVCpusThenReturnVCpus() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .vcpus(1)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals(1, flavor.getVcpus());
    }

    @Test
    public void whenUpdateHasRamThenReturnRam() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .ram(1)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals(1, flavor.getRam());
    }

    @Test
    public void whenUpdateHasDiskThenReturnDisk() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .disk(1)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals(1, flavor.getDisk());
    }

    @Test
    public void whenUpdateHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .inboundBandwidth(1)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals(1, flavor.getInboundBandwidth());
    }

    @Test
    public void whenUpdateHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(flavor.getId())
                .outboundBandwidth(1)
                .build();

        Flavor flavor = flavorService.handleUpdated(event);

        Assertions.assertEquals(1, flavor.getOutboundBandwidth());
    }
}
