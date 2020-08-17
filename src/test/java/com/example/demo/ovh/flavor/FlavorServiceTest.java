package com.example.demo.ovh.flavor;

import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.flavor.service.model.FlavorUpdateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.sample.TestFlavorUtil;
import com.example.demo.sample.TestRegionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FlavorServiceTest {

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private IRegionService regionService;

    @Test
    @Order(1)
    public void testExistsAllShouldBeFalse() {

        boolean exists = flavorService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void testExistsAllShouldBeTrue() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("flavor-exist-all-be-true");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorCreateRequest flavorCreateRequest = TestFlavorUtil.createFlavor("exists-all-should-be-true", region.getName());
        flavorService.handleFlavorCreate(flavorCreateRequest);

        boolean exists = flavorService.existsAny();

        Assertions.assertTrue(exists);
    }

    @Test
    public void testGetByFlavorIdShouldBeFalse() {

        boolean exists = flavorService.existsByFlavorId("get-by-flavor-id-should-be-false");

        Assertions.assertFalse(exists);
    }

    @Test
    public void textGetByFlavorIdShouldBeTrue() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("get-by-flavor-id-should-be-true");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorCreateRequest flavorCreateRequest = TestFlavorUtil.createFlavor("get-by-flavor-id-should-be-true", region.getName());
        Flavor flavor = flavorService.handleFlavorCreate(flavorCreateRequest);

        boolean exists = flavorService.existsByFlavorId(flavor.getFlavorId());

        Assertions.assertTrue(exists);
    }

    @Test
    public void testHandleFlavorCreate() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("handle-flavor-create");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorCreateRequest flavorCreateRequest = FlavorCreateRequest.builder()
                .flavorId("handle-flavor-create")
                .regionName(region.getName())
                .name("handle-create-flavor")
                .type("default-type")
                .available(true)
                .quota(1)
                .hourly("default-hourly")
                .monthly("default-monthly")
                .osType("default-os")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1)
                .build();
        Flavor flavor = flavorService.handleFlavorCreate(flavorCreateRequest);

        Assertions.assertNotNull(flavor.getId());
        Assertions.assertEquals(flavorCreateRequest.getFlavorId(), flavor.getFlavorId());
        Assertions.assertEquals(flavorCreateRequest.getName(), flavor.getName());
        Assertions.assertEquals(flavorCreateRequest.getType(), flavor.getType());
        Assertions.assertEquals(flavorCreateRequest.getAvailable(), flavor.getAvailable());
        Assertions.assertEquals(flavorCreateRequest.getQuota(), flavor.getQuota());
        Assertions.assertEquals(flavorCreateRequest.getHourly(), flavor.getHourly());
        Assertions.assertEquals(flavorCreateRequest.getMonthly(), flavor.getMonthly());
        Assertions.assertEquals(flavorCreateRequest.getOsType(), flavor.getOsType());
        Assertions.assertEquals(flavorCreateRequest.getVcpus(), flavor.getVcpus());
        Assertions.assertEquals(flavorCreateRequest.getRam(), flavor.getRam());
        Assertions.assertEquals(flavorCreateRequest.getDisk(), flavor.getDisk());
        Assertions.assertEquals(flavorCreateRequest.getInboundBandwidth(), flavor.getInboundBandwidth());
        Assertions.assertEquals(flavorCreateRequest.getOutboundBandwidth(), flavorCreateRequest.getOutboundBandwidth());
    }

    @Test
    public void testHandleFlavorUpdate() {

        RegionCreateRequest regionCreateRequest = TestRegionUtil.createRegion("handle-flavor-update");
        Region region = regionService.handleRegionCreate(regionCreateRequest);

        FlavorCreateRequest flavorCreateRequest = FlavorCreateRequest.builder()
                .flavorId("handle-flavor-update")
                .regionName(region.getName())
                .name("handle-flavor-update")
                .type("default-type")
                .available(true)
                .quota(1)
                .hourly("default-hourly")
                .monthly("default-monthly")
                .osType("default-os")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1)
                .build();
        Flavor flavor = flavorService.handleFlavorCreate(flavorCreateRequest);

        RegionCreateRequest updatedRegionCreateRequest = TestRegionUtil.createRegion("handle-flavor-updated");
        Region updatedRegion = regionService.handleRegionCreate(updatedRegionCreateRequest);

        FlavorUpdateRequest flavorUpdateRequest = FlavorUpdateRequest.builder()
                .flavorId("handle-flavor-update")
                .regionName(updatedRegion.getName())
                .name("handle-flavor-updated")
                .type("updated-type")
                .quota(2)
                .available(false)
                .hourly("updated-hourly")
                .monthly("updated-hourly")
                .osType("updated-os")
                .vcpus(2)
                .ram(2)
                .disk(2)
                .inboundBandwidth(2)
                .outboundBandwidth(2)
                .build();
        Flavor updatedFlavor = flavorService.handleFlavorUpdate(flavorUpdateRequest);

        Assertions.assertEquals(flavor.getId(), updatedFlavor.getId());
        Assertions.assertEquals(flavor.getFlavorId(), updatedFlavor.getFlavorId());
        Assertions.assertNotEquals(flavor.getName(), updatedFlavor.getName());
        Assertions.assertNotEquals(flavor.getType(), updatedFlavor.getType());
        Assertions.assertNotEquals(flavor.getAvailable(), updatedFlavor.getAvailable());
        Assertions.assertNotEquals(flavor.getQuota(), updatedFlavor.getQuota());
        Assertions.assertNotEquals(flavor.getHourly(), updatedFlavor.getHourly());
        Assertions.assertNotEquals(flavor.getMonthly(), updatedFlavor.getMonthly());
        Assertions.assertNotEquals(flavor.getOsType(), updatedFlavor.getOsType());
        Assertions.assertNotEquals(flavor.getVcpus(), updatedFlavor.getVcpus());
        Assertions.assertNotEquals(flavor.getRam(), updatedFlavor.getRam());
        Assertions.assertNotEquals(flavor.getDisk(), updatedFlavor.getDisk());
        Assertions.assertNotEquals(flavor.getInboundBandwidth(), updatedFlavor.getInboundBandwidth());
        Assertions.assertNotEquals(flavor.getOutboundBandwidth(), updatedFlavor.getInboundBandwidth());

        Assertions.assertEquals(flavorUpdateRequest.getFlavorId(), updatedFlavor.getFlavorId());
        Assertions.assertEquals(flavorUpdateRequest.getName(), updatedFlavor.getName());
        Assertions.assertEquals(flavorUpdateRequest.getType(), updatedFlavor.getType());
        Assertions.assertEquals(flavorUpdateRequest.getAvailable(), updatedFlavor.getAvailable());
        Assertions.assertEquals(flavorUpdateRequest.getQuota(), updatedFlavor.getQuota());
        Assertions.assertEquals(flavorUpdateRequest.getHourly(), updatedFlavor.getHourly());
        Assertions.assertEquals(flavorUpdateRequest.getMonthly(), updatedFlavor.getMonthly());
        Assertions.assertEquals(flavorUpdateRequest.getOsType(), updatedFlavor.getOsType());
        Assertions.assertEquals(flavorUpdateRequest.getVcpus(), updatedFlavor.getVcpus());
        Assertions.assertEquals(flavorUpdateRequest.getRam(), updatedFlavor.getRam());
        Assertions.assertEquals(flavorUpdateRequest.getDisk(), updatedFlavor.getDisk());
        Assertions.assertEquals(flavorUpdateRequest.getInboundBandwidth(), updatedFlavor.getInboundBandwidth());
        Assertions.assertEquals(flavorUpdateRequest.getOutboundBandwidth(), updatedFlavor.getOutboundBandwidth());
    }
}
