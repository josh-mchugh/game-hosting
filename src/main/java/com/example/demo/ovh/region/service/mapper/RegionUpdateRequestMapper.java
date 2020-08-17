package com.example.demo.ovh.region.service.mapper;

import com.example.demo.ovh.feign.region.model.RegionApi;
import com.example.demo.ovh.region.service.model.RegionUpdateRequest;
import com.google.common.base.Joiner;
import org.apache.commons.collections4.CollectionUtils;

public class RegionUpdateRequestMapper {

    public static RegionUpdateRequest map(RegionApi regionResponse) {

        RegionUpdateRequest.Builder builder =  RegionUpdateRequest.builder()
                .name(regionResponse.getName())
                .continentCode(regionResponse.getContinentCode())
                .dataCenterLocation(regionResponse.getDataCenterLocation())
                .status(regionResponse.getStatus());

        if (CollectionUtils.isNotEmpty(regionResponse.getIpCountries())) {

            builder.countryCodes(Joiner.on(",").join(regionResponse.getIpCountries()));
        }

        return builder.build();
    }
}
