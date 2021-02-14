package com.example.demo.ovh.region.entity.mapper;

import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.ovh.region.entity.model.Region;

public class RegionMapper {

    public static Region map(RegionEntity entity) {

        if(entity == null) {

            return null;
        }

        return Region.builder()
                .id(entity.getUUID())
                .name(entity.getName())
                .continentCode(entity.getContinentCode())
                .countryCodes(entity.getCountryCodes())
                .dataCenterLocation(entity.getDataCenterLocation())
                .status(entity.getStatus())
                .build();
    }
}
