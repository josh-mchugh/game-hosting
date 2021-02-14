package com.example.demo.ovh.flavor.entity.mapper;

import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.model.Flavor;

public class FlavorMapper {

    public static Flavor map(FlavorEntity entity) {

        if(entity == null) {

            return null;
        }

        return Flavor.builder()
                .id(entity.getId())
                .ovhId(entity.getOvhId())
                .name(entity.getName())
                .type(entity.getType())
                .available(entity.getAvailable())
                .hourly(entity.getHourly())
                .monthly(entity.getMonthly())
                .quota(entity.getQuota())
                .osType(entity.getOsType())
                .vcpus(entity.getVcpus())
                .ram(entity.getRam())
                .disk(entity.getDisk())
                .inboundBandwidth(entity.getInboundBandwidth())
                .outboundBandwidth(entity.getOutboundBandwidth())
                .build();
    }
}
