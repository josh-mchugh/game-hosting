package com.example.demo.ovh.flavor.entity;

import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.entity.service.IFlavorService;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorServiceCreatedTest {

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private Region region;

    @BeforeEach
    public void setup() {

        region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();
    }

    @Test
    public void whenCreateIsValidThenReturnExpected() {

        UUID id = UUID.randomUUID();

        FlavorCreatedEvent event = flavorCreatedBuilder(id)
                .regionId(region.getId())
                .flavorId("flavor-id")
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

        Flavor flavor = flavorService.handleCreated(event);

        Flavor expected = Flavor.builder()
                .id(id.toString())
                .flavorId("flavor-id")
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
    public void whenCreateHasMinValidThenReturnMinExpected() {

        UUID id = UUID.randomUUID();

        FlavorCreatedEvent event = flavorCreatedBuilder(id)
                .regionId(region.getId())
                .flavorId("flavor-id")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Flavor expected = Flavor.builder()
                .id(id.toString())
                .flavorId("flavor-id")
                .build();

        Assertions.assertEquals(expected, flavor);
    }

    @Test
    public void whenCreateHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> flavorService.handleCreated(null));
    }

    @Test
    public void whenCreateHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FlavorCreatedEvent event = flavorCreatedBuilder(id).build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals(id.toString(), flavor.getId());
    }

    @Test
    public void whenCreateHasNullIdThenThrowException() {

        FlavorCreatedEvent event = flavorCreatedBuilder(null).build();

        Assertions.assertThrows(NullPointerException.class, () -> flavorService.handleCreated(event));
    }

    @Test
    public void whenCreateHasRegionIdThenReturnNotNull() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .regionId(region.getId())
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertNotNull(flavor);
    }

    @Test
    public void whenCreateHasNullRegionIdThenThrowException() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .regionId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> flavorService.handleCreated(event));
    }

    @Test
    public void whenCreateHasInvalidRegionIdThenThrowException() {

        FlavorCreatedEvent event  = flavorCreatedBuilder()
                .regionId("wrong-id")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> flavorService.handleCreated(event));
    }

    @Test
    public void whenCreateHasFlavorIdThenReturnFlavorId() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .flavorId("flavor-id")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals("flavor-id", flavor.getFlavorId());
    }

    @Test
    public void whenCreateHasNullFlavorIdThenThrowException() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .flavorId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> flavorService.handleCreated(event));
    }

    @Test
    public void whenCreateHasNameThenReturnName() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .name("name")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals("name", flavor.getName());
    }

    @Test
    public void whenCreateHasTypeThenReturnType() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .type("type")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals("type", flavor.getType());
    }

    @Test
    public void whenCreateHasAvailableThenReturnAvailable() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .available(true)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertTrue(flavor.getAvailable());
    }

    @Test
    public void whenCreateHasHourlyThenReturnHourly() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .hourly("hourly")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals("hourly", flavor.getHourly());
    }

    @Test
    public void whenCreateHasMonthlyThenReturnMonthly() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .monthly("monthly")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals("monthly", flavor.getMonthly());
    }

    @Test
    public void whenCreateHasQuotaThenReturnQuota() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .quota(1)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals(1, flavor.getQuota());
    }

    @Test
    public void whenCreateHasOsTypeThenReturnOsType() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .osType("osType")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals("osType", flavor.getOsType());
    }

    @Test
    public void whenCreateHasVCpusThenReturnVCpus() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .vcpus(1)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals(1, flavor.getVcpus());
    }

    @Test
    public void whenCreateHasRamThenReturnRam() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .ram(1)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals(1, flavor.getRam());
    }

    @Test
    public void whenCreateHasDiskThenReturnDisk() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .disk(1)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals(1, flavor.getDisk());
    }

    @Test
    public void whenCreateHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .inboundBandwidth(1)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals(1, flavor.getInboundBandwidth());
    }

    @Test
    public void whenCreateHasOutboundBandwidthThenReturnOutboundBandWidth() {

        FlavorCreatedEvent event = flavorCreatedBuilder()
                .outboundBandwidth(1)
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Assertions.assertEquals(1, flavor.getOutboundBandwidth());
    }

    private FlavorCreatedEvent.Builder flavorCreatedBuilder() {

        return flavorCreatedBuilder(UUID.randomUUID());
    }

    private FlavorCreatedEvent.Builder flavorCreatedBuilder(UUID id) {

        return FlavorCreatedEvent.builder()
                .id(id)
                .flavorId("handle-flavor-create")
                .regionId(region.getId());
    }
}
