package com.example.demo.ovh.flavor.scheduler.projection.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FlavorProjection model = new FlavorProjection(id.toString(), null, null, null, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), "name", null, null, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, "type", null, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasAvailableThenReturnAvailable() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, true, null, null, null, null, null, null, null, null, null);

        Assertions.assertTrue(model.getAvailable());
    }

    @Test
    public void whenModelHasHourlyThenReturnHourly() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, "hourly", null, null, null, null, null, null, null, null);

        Assertions.assertEquals("hourly", model.getHourly());
    }

    @Test
    public  void whenModelHasMonthlyThenReturnMonthly() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, "monthly", null, null, null, null, null, null, null);

        Assertions.assertEquals("monthly", model.getMonthly());
    }

    @Test
    public void whenModelHasQuotaThenReturnQuota() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, 1, null, null, null, null, null, null);

        Assertions.assertEquals(1, model.getQuota());
    }

    @Test
    public void whenModelHasOsTypeThenReturnOsType() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, "osType", null, null, null, null, null);

        Assertions.assertEquals("osType", model.getOsType());
    }

    @Test
    public void whenModelHasVcpusThenReturnVCpus() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, 1, null, null, null, null);

        Assertions.assertEquals(1, model.getVcpus());
    }

    @Test
    public void whenModelHasRamThenReturnRam() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, 1, null, null, null);

        Assertions.assertEquals(1, model.getRam());
    }

    @Test
    public void whenModelHasDiskThenReturnDisk() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, 1, null, null);

        Assertions.assertEquals(1, model.getDisk());
    }

    @Test
    public void whenModelHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, null, 1, null);

        Assertions.assertEquals(1, model.getInboundBandwidth());
    }

    @Test
    public void whenModelHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorProjection model = new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, null, null, 1);

        Assertions.assertEquals(1, model.getOutboundBandwidth());
    }

    @Test
    public void whenModelToString() {

        FlavorProjection model = model();

        String expected = "FlavorProjection(id=bc24440a-0e14-4ee0-8905-bcadb2d954cd, name=name, type=type, available=true, hourly=hourly, monthly=monthly, quota=1, osType=osType, vcpus=1, ram=1, disk=1, inboundBandwidth=1, outboundBandwidth=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FlavorProjection model = model();

        Assertions.assertEquals(-1813506663, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FlavorProjection model1 = model();
        FlavorProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FlavorProjection model = model();

        Assertions.assertNotEquals(model, new FlavorProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, null, null, null));
    }

    private FlavorProjection model() {

        return new FlavorProjection(UUID.fromString("bc24440a-0e14-4ee0-8905-bcadb2d954cd").toString(), "name", "type", true, "hourly", "monthly", 1, "osType", 1, 1, 1, 1, 1);
    }
}
