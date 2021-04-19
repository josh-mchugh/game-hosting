package com.example.demo.ovh.flavor.feign.model;

import com.example.demo.ovh.feign.PlanCodeApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlavorApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        FlavorApi model = new FlavorApi();
        model.setId("id");

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        FlavorApi model = new FlavorApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasAvailableThenReturnAvailable() {

        FlavorApi model = new FlavorApi();
        model.setAvailable(true);

        Assertions.assertTrue(model.isAvailable());
    }

    @Test
    public void whenModelHasDiskThenReturnDisk() {

        FlavorApi model = new FlavorApi();
        model.setDisk(1);

        Assertions.assertEquals(1, model.getDisk());
    }

    @Test
    public void whenModelHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorApi model = new FlavorApi();
        model.setInboundBandwidth(1);

        Assertions.assertEquals(1, model.getInboundBandwidth());
    }

    @Test
    public void whenModelHasOsTypeThenReturnOsType() {

        FlavorApi model = new FlavorApi();
        model.setOsType("osType");

        Assertions.assertEquals("osType", model.getOsType());
    }

    @Test
    public void whenModelHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorApi model = new FlavorApi();
        model.setOutboundBandwidth(1);

        Assertions.assertEquals(1, model.getOutboundBandwidth());
    }

    @Test
    public void whenModelHasPlanCodesThenReturnPlanCodes() {

        PlanCodeApi planCodeApi = new PlanCodeApi();

        FlavorApi model = new FlavorApi();
        model.setPlanCodes(planCodeApi);

        Assertions.assertEquals(planCodeApi, model.getPlanCodes());
    }

    @Test
    public void whenModelHasQuotaThenReturnQuota() {

        FlavorApi model = new FlavorApi();
        model.setQuota(1);

        Assertions.assertEquals(1, model.getQuota());
    }

    @Test
    public void whenModelHasRamThenReturnRam() {

        FlavorApi model = new FlavorApi();
        model.setRam(1);

        Assertions.assertEquals(1, model.getRam());
    }

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        FlavorApi model = new FlavorApi();
        model.setRegionName("regionName");

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        FlavorApi model = new FlavorApi();
        model.setType("type");

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasVcpusThenReturnVcpus() {

        FlavorApi model = new FlavorApi();
        model.setVcpus(1);

        Assertions.assertEquals(1, model.getVcpus());
    }

    @Test
    public void whenModelToString() {

        FlavorApi model = model();

        String expected = "FlavorApi(id=id, name=name, available=true, disk=1, inboundBandwidth=1, osType=osType, outboundBandwidth=1, planCodes=PlanCodeApi(hourly=null, monthly=null), quota=1, ram=1, regionName=regionName, type=type, vcpus=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FlavorApi model = model();

        Assertions.assertEquals(-1971396128, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FlavorApi model1 = model();
        FlavorApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FlavorApi model = model();

        Assertions.assertNotEquals(model, new FlavorApi());
    }

    private FlavorApi model() {

        FlavorApi model = new FlavorApi();
        model.setId("id");
        model.setName("name");
        model.setAvailable(true);
        model.setDisk(1);
        model.setInboundBandwidth(1);
        model.setOsType("osType");
        model.setOutboundBandwidth(1);
        model.setPlanCodes(new PlanCodeApi());
        model.setQuota(1);
        model.setRam(1);
        model.setRegionName("regionName");
        model.setType("type");
        model.setVcpus(1);

        return model;
    }
}
