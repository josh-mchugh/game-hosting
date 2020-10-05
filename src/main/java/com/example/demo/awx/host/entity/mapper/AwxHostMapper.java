package com.example.demo.awx.host.entity.mapper;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.host.entity.model.AwxHost;

public class AwxHostMapper {

    public static AwxHost map(AwxHostEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxHost.builder()
                .id(entity.getId())
                .hostId(entity.getHostId())
                .hostname(entity.getHostname())
                .description(entity.getDescription())
                .enabled(entity.getEnabled())
                .build();
    }
}
