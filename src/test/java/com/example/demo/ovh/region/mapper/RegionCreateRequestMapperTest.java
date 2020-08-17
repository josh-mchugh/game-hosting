package com.example.demo.ovh.region.mapper;

import com.example.demo.ovh.feign.model.OvhRegionApiResponse;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.service.mapper.RegionCreateRequestMapper;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RegionCreateRequestMapperTest {

    @Test
    public void whenRegionResponseHasNameThenCreateRequestHasName() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setName("test-name");

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertEquals("test-name", regionCreateRequest.getName());
    }

    @Test
    public void whenRegionResponseNameNullThenCreateRequestNameNull() {

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionCreateRequest.getName());
    }

    @Test
    public void whenRegionResponseHasContinentCodeThenCreateRequestHasContinentCode() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setContinentCode("US");

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertEquals("US", regionCreateRequest.getContinentCode());
    }

    @Test
    public void whenRegionResponseContinentCodeNullThenCreateRequestContinentCodeNull() {

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionCreateRequest.getContinentCode());
    }

    @Test
    public void whenRegionResponseHasDatacenterLocationThenCreateRequestHasDatacenterLocation() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setDataCenterLocation("US");

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertEquals("US", regionCreateRequest.getDataCenterLocation());
    }

    @Test
    public void whenRegionResponseHasNullDatacenterLocationThenCreateRequestHasNullDatacenterLocation() {

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionCreateRequest.getDataCenterLocation());
    }

    @Test
    public void whenRegionResponseStatusHasUpThenCreateRequestHasStatusUp() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setStatus(RegionStatus.UP);

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.UP, regionCreateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusDownThenCreateRequestHasStatusDown() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setStatus(RegionStatus.DOWN);

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.DOWN, regionCreateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusMaintenanceThenCreateStatusHasStatusMaintenance() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setStatus(RegionStatus.MAINTENANCE);

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertEquals(RegionStatus.MAINTENANCE, regionCreateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasStatusNullThenCreateStatusHasStatusNull() {

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionCreateRequest.getStatus());
    }

    @Test
    public void whenRegionResponseHasCountryIdThenCreateRequestConcatCountryCodes() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setIpCountries(Arrays.asList("us", "uk", "ca", "es"));

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertEquals("us,uk,ca,es", regionCreateRequest.getCountryCodes());
    }

    @Test
    public void whenRegionResponseHasNullCountryIdThenCreateRequestCountryCodesNull() {

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(new OvhRegionApiResponse());

        Assertions.assertNull(regionCreateRequest.getCountryCodes());
    }

    @Test
    public void whenRegionResponseHasEmptyCountryIdsThenCreateRequestCountryCodesNull() {

        OvhRegionApiResponse regionResponse = new OvhRegionApiResponse();
        regionResponse.setIpCountries(new ArrayList<>());

        RegionCreateRequest regionCreateRequest = RegionCreateRequestMapper.map(regionResponse);

        Assertions.assertNull(regionCreateRequest.getCountryCodes());
    }
}
