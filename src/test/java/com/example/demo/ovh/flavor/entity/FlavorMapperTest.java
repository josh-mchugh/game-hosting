package com.example.demo.ovh.flavor.entity;

import com.example.demo.ovh.flavor.entity.mapper.FlavorMapper;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorMapperTest {

    @Test
    public void whenEntityIsValidThenReturnExpected() {

        UUID id = UUID.randomUUID();

        FlavorEntity entity = new FlavorEntity();
        entity.setId(id);
        entity.setFlavorId("flavor-id");
        entity.setName("name");
        entity.setType("type");
        entity.setAvailable(true);
        entity.setHourly("hourly");
        entity.setMonthly("monthly");
        entity.setQuota(1);
        entity.setOsType("osType");
        entity.setVcpus(1);
        entity.setRam(1);
        entity.setDisk(1);
        entity.setInboundBandwidth(1);
        entity.setOutboundBandwidth(1);

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

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals(expected, flavor);
    }

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(FlavorMapper.map(null));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        FlavorEntity entity = new FlavorEntity();
        entity.setId("id");

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals("id", flavor.getId());
    }

    @Test
    public void whenEntityHasFlavorIdThenReturnFlavorId() {

        FlavorEntity entity = new FlavorEntity();
        entity.setFlavorId("flavor-id");

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals("flavor-id", flavor.getFlavorId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        FlavorEntity entity = new FlavorEntity();
        entity.setName("name");

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals("name", flavor.getName());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        FlavorEntity entity = new FlavorEntity();
        entity.setType("type");

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals("type", flavor.getType());
    }

    @Test
    public void whenEntityHasAvailableThenReturnAvailable() {

        FlavorEntity entity = new FlavorEntity();
        entity.setAvailable(false);

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertFalse(flavor.getAvailable());
    }

    @Test
    public void whenEntityHasHourlyThenReturnHourly() {

        FlavorEntity entity = new FlavorEntity();
        entity.setHourly("hourly");

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals("hourly", flavor.getHourly());
    }

    @Test
    public void whenEntityHasMonthlyThenReturnMonthly() {

        FlavorEntity entity = new FlavorEntity();
        entity.setMonthly("monthly");

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals("monthly", flavor.getMonthly());
    }

    @Test
    public void whenEntityHasQuotaThenReturnQuota() {

        FlavorEntity entity = new FlavorEntity();
        entity.setQuota(1);

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals(1, flavor.getQuota());
    }

    @Test
    public void whenEntityHasOsTypeThenReturnOsType() {

        FlavorEntity entity = new FlavorEntity();
        entity.setOsType("osType");

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals("osType", flavor.getOsType());
    }

    @Test
    public void whenEntityHasVCpusThenReturnVCpus() {

        FlavorEntity entity = new FlavorEntity();
        entity.setVcpus(1);

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals(1, flavor.getVcpus());
    }

    @Test
    public void whenEntityHasRamThenReturnRam() {

        FlavorEntity entity = new FlavorEntity();
        entity.setRam(1);

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals(1, flavor.getRam());
    }

    @Test
    public void whenEntityHasDiskThenReturnDisk() {

        FlavorEntity entity = new FlavorEntity();
        entity.setDisk(1);

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals(1, flavor.getDisk());
    }

    @Test
    public void whenEntityHasInboundBandwidthThenReturnInboundBandwidth() {

        FlavorEntity entity = new FlavorEntity();
        entity.setInboundBandwidth(1);

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals(1, flavor.getInboundBandwidth());
    }

    @Test
    public void whenEntityHasOutboundBandwidthThenReturnOutboundBandwidth() {

        FlavorEntity entity = new FlavorEntity();
        entity.setOutboundBandwidth(1);

        Flavor flavor = FlavorMapper.map(entity);

        Assertions.assertEquals(1, flavor.getOutboundBandwidth());
    }
}
