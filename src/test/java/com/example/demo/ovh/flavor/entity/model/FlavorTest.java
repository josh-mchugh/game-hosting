package com.example.demo.ovh.flavor.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        Flavor flavor = Flavor.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, flavor.getId());
    }

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        Flavor flavor = Flavor.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", flavor.getOvhId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        Flavor flavor = Flavor.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", flavor.getName());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        Flavor flavor = Flavor.builder()
                .type("type")
                .build();

        Assertions.assertEquals("type", flavor.getType());
    }

    @Test
    public void whenModelHasAvailableThenReturnAvailable() {

        Flavor flavor = Flavor.builder()
                .available(true)
                .build();

        Assertions.assertTrue(flavor.getAvailable());
    }

    @Test
    public void whenModelHasHourlyThenReturnHourly() {

        Flavor flavor = Flavor.builder()
                .hourly("hourly")
                .build();

        Assertions.assertEquals("hourly", flavor.getHourly());
    }

    @Test
    public void whenModelHasMonthlyThenReturnMonthly() {

        Flavor flavor = Flavor.builder()
                .monthly("monthly")
                .build();

        Assertions.assertEquals("monthly", flavor.getMonthly());
    }

    @Test
    public void whenModelHasQuotaThenReturnQuota() {

        Flavor flavor = Flavor.builder()
                .quota(1)
                .build();

        Assertions.assertEquals(1, flavor.getQuota());
    }

    @Test
    public void whenModelHasOsTypeThenReturnOsType() {

        Flavor flavor = Flavor.builder()
                .osType("osType")
                .build();

        Assertions.assertEquals("osType", flavor.getOsType());
    }

    @Test
    public void whenModelHasVcpusThenReturnVcpus() {

        Flavor flavor = Flavor.builder()
                .vcpus(1)
                .build();

        Assertions.assertEquals(1, flavor.getVcpus());
    }

    @Test
    public void whenModelHasRamThenReturnRam() {

        Flavor flavor = Flavor.builder()
                .ram(1)
                .build();

        Assertions.assertEquals(1, flavor.getRam());
    }

    @Test
    public void whenModelHasDiskThenReturnDisk() {

        Flavor flavor = Flavor.builder()
                .disk(1)
                .build();

        Assertions.assertEquals(1, flavor.getDisk());
    }

    @Test
    public void whenModelHasInboundBandwidthThenReturnInboundBandwidth() {

        Flavor flavor = Flavor.builder()
                .inboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, flavor.getInboundBandwidth());
    }

    @Test
    public void whenModelHasOutboundBandwidthThenReturnOutboundBandwidth() {

        Flavor flavor = Flavor.builder()
                .outboundBandwidth(1)
                .build();

        Assertions.assertEquals(1, flavor.getOutboundBandwidth());
    }

    @Test
    public void whenModelToString() {

        Flavor model = model();

        String expected = "Flavor(id=87fdfaa2-7c1c-468e-a132-6bd5cded462c, ovhId=ovhId, name=name, type=type, available=true, hourly=hourly, monthly=monthly, quota=1, osType=osType, vcpus=1, ram=1, disk=1, inboundBandwidth=1, outboundBandwidth=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        Flavor model = model();

        Assertions.assertEquals(1764163050, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        Flavor model1 = model();
        Flavor model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        Flavor flavor = model();

        Assertions.assertNotEquals(flavor, Flavor.builder().build());
    }

    private Flavor model() {

        return Flavor.builder()
                .id(UUID.fromString("87fdfaa2-7c1c-468e-a132-6bd5cded462c"))
                .ovhId("ovhId")
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
    }
}
