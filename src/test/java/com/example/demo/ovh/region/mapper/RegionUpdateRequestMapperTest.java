package com.example.demo.ovh.region.mapper;

import com.example.demo.ovh.feign.model.OvhRegionApiResponse;
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

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setName("test-name");

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("test-name", regionUpdateRequest.getName());
    }

    @Test
    public void whenRegionResponseNameNullThenUpdateRequestNameNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionUpdateRequest.getName());
    }

    @Test
    public void whenRegionResponseHasContinentCodeThenUpdateRequestHasContinentCode() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setContinentCode("US");

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("US", regionUpdateRequest.getContinentCode());
    }

    @Test
    public void whenRegionResponseContinentCodeNullThenUpdateRequestContinentCodeNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionUpdateRequest.getContinentCode());
    }

    @Test
    public void whenRegionResponseHasDatacenterLocationThenUpdateRequestHasDatacenterLocation() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setDataCenterLocation("US");

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("US", regionUpdateRequest.getDataCenterLocation());
    }

    @Test
    public void whenRegionResponseHasNullDatacenterLocationThenUpdateRequestHasNullDatacenterLocation() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionUpdateRequest.getDataCenterLocation());
    }

    @Test
    public void whenRegionResponseStatusHasUpThenUpdateRequestHasStatusUp() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setStatus(RegionStatus.UP);

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.UP, regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusDownThenUpdateRequestHasStatusDown() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setStatus(RegionStatus.DOWN);

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.DOWN, regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusMaintenanceThenUpdateRequestStatusHasStatusMaintenance() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setStatus(RegionStatus.MAINTENANCE);

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.MAINTENANCE, regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusNullThenUpdateRequestStatusHasStatusNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionUpdateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasCountryIdThenUpdateRequestConcatCountryCodes() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setIpCountries(Arrays.asList("us", "uk", "ca", "es"));

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertEquals("us,uk,ca,es", regionUpdateRequest.getCountryCodes());
    }

    @Test
    public void whenRegionResponseHasNullCountryIdThenUpdateRequestCountryCodesNull() {

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionUpdateRequest.getCountryCodes());
    }

    @Test
    public void whenRegionResponseHasEmptyCountryIdsThenUpdateRequestCountryCodesNull() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setIpCountries(new ArrayList<>());

        RegionUpdateRequest regionUpdateRequest = RegionUpdateRequestMapper.map(regionResponse);

        Assertions.assertNull(regionUpdateRequest.getCountryCodes());
    }
}
