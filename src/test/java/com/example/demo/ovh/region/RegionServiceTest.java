package com.example.demo.ovh.region;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.ovh.region.service.model.RegionUpdateRequest;
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
public class RegionServiceTest {

    @Autowired
    private IRegionService regionService;

    @Test
    @Order(1)
    public void testExistsAnyShouldBeFalse() {

        boolean exists = regionService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void testExistAnyShouldBeTrue() {

        RegionCreateRequest request = TestRegionUtil.createRegion("exits-any-be-true");
        regionService.handleRegionCreate(request);

        boolean exists = regionService.existsAny();

        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByNameShouldBeTrue() {

        RegionCreateRequest request = TestRegionUtil.createRegion("exist-by-name-be-true");
        regionService.handleRegionCreate(request);

        boolean exists = regionService.existsByName("exist-by-name-be-true");

        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByNameShouldByFalse() {

        boolean exists = regionService.existsByName("exist-by-name-be-false");

        Assertions.assertFalse(exists);
    }

    @Test
    public void testHandleRegionCreate() {

        RegionCreateRequest request = RegionCreateRequest.builder()
                .name("handle-region-create")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleRegionCreate(request);

        Assertions.assertNotNull(region.getId());
        Assertions.assertEquals(request.getName(), region.getName());
        Assertions.assertEquals(request.getContinentCode(), region.getContinentCode());
        Assertions.assertEquals(request.getCountryCodes(), region.getCountryCodes());
        Assertions.assertEquals(request.getStatus(), region.getStatus());
    }

    @Test
    public void testHandleRegionUpdate() {

        RegionCreateRequest createRequest = RegionCreateRequest.builder()
                .name("handle-region-update")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("us")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleRegionCreate(createRequest);

        RegionUpdateRequest updateRequest = RegionUpdateRequest.builder()
                .name("handle-region-update")
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleRegionUpdate(updateRequest);

        Assertions.assertEquals(region.getId(), updatedRegion.getId());
        Assertions.assertEquals(region.getName(), updatedRegion.getName());

        Assertions.assertNotEquals(region.getContinentCode(), updatedRegion.getContinentCode());
        Assertions.assertEquals(updateRequest.getContinentCode(), updatedRegion.getContinentCode());

        Assertions.assertNotEquals(region.getCountryCodes(), updatedRegion.getCountryCodes());
        Assertions.assertEquals(updateRequest.getCountryCodes(), updatedRegion.getCountryCodes());

        Assertions.assertNotEquals(region.getDataCenterLocation(), updatedRegion.getDataCenterLocation());
        Assertions.assertEquals(updateRequest.getDataCenterLocation(), updatedRegion.getDataCenterLocation());

        Assertions.assertNotEquals(region.getStatus(), updatedRegion.getStatus());
        Assertions.assertEquals(updatedRegion.getStatus(), updatedRegion.getStatus());
    }
}
