package com.example.demo.ovh.region.service.mapper;

import com.example.demo.ovh.feign.model.OvhRegionApiResponse;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.google.common.base.Joiner;
import org.apache.commons.collections4.CollectionUtils;

public class RegionCreateRequestMapper {

    public static RegionCreateRequest map(OvhRegionApiResponse regionResponse) {

        RegionCreateRequest.Builder builder = RegionCreateRequest.builder()
                .name(regionResponse.getName())
                .continentCode(regionResponse.getContinentCode())
                .dataCenterLocation(regionResponse.getDataCenterLocation())
                .status(regionResponse.getStatus());

        if(CollectionUtils.isNotEmpty(regionResponse.getIpCountries())) {

            builder.countryCodes(Joiner.on(",").join(regionResponse.getIpCountries()));
        }

        return builder.build();
    }
}
