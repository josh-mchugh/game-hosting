package com.example.demo.ovh.region.mapper;

import com.example.demo.ovh.feign.region.model.RegionApi;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.service.mapper.RegionUpdateRequestMapper;
import com.example.demo.ovh.region.service.model.RegionUpdateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RegionUpdateRequestMapperTest {

    @Test
    public void whenRegionResponseHasNameThenUpdateRequestHasName() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setName("test-name");

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("test-name", regionUpdateRequest.getName());
    }

    @Test
    public void whenRegionResponseNameNullThenUpdateRequestNameNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new RegionApi());

        Assertions.assertNull(regionUpdateRequest.getName());
    }

    @Test
    public void whenRegionResponseHasContinentCodeThenUpdateRequestHasContinentCode() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setContinentCode("US");

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("US", regionUpdateRequest.getContinentCode());
    }

    @Test
    public void whenRegionResponseContinentCodeNullThenUpdateRequestContinentCodeNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new RegionApi());

        Assertions.assertNull(regionUpdateRequest.getContinentCode());
    }

    @Test
    public void whenRegionResponseHasDatacenterLocationThenUpdateRequestHasDatacenterLocation() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setDataCenterLocation("US");

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("US", regionUpdateRequest.getDataCenterLocation());
    }

    @Test
    public void whenRegionResponseHasNullDatacenterLocationThenUpdateRequestHasNullDatacenterLocation() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new RegionApi());

        Assertions.assertNull(regionUpdateRequest.getDataCenterLocation());
    }

    @Test
    public void whenRegionResponseStatusHasUpThenUpdateRequestHasStatusUp() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setStatus(RegionStatus.UP);

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.UP, regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusDownThenUpdateRequestHasStatusDown() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setStatus(RegionStatus.DOWN);

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.DOWN, regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusMaintenanceThenUpdateRequestStatusHasStatusMaintenance() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setStatus(RegionStatus.MAINTENANCE);

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.MAINTENANCE, regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusNullThenUpdateRequestStatusHasStatusNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new RegionApi());

        Assertions.assertNull(regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasCountryIdThenUpdateRequestConcatCountryCodes() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setIpCountries(Arrays.asList("us", "uk", "ca", "es"));

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("us,uk,ca,es", regionUpdateRequest.getCountryCodes());
    }

    @Test
    public void whenRegionResponseHasNullCountryIdThenUpdateRequestCountryCodesNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new RegionApi());

        Assertions.assertNull(regionUpdateRequest.getCountryCodes());
    }

    @Test
    public void whenRegionResponseHasEmptyCountryIdsThenUpdateRequestCountryCodesNull() {

        RegionApi regionResponse = new RegionApi();
        regionResponse.setIpCountries(new ArrayList<>());

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertNull(regionUpdateRequest.getCountryCodes());
    }
}
