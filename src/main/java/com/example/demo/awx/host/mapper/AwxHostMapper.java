package com.example.demo.awx.host.mapper;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.host.model.AwxHost;

public class AwxHostMapper {

    public static AwxHost map(AwxHostEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxHost.builder()
                .id(entity.getId())
                .hostname(entity.getHostname())
                .description(entity.getDescription())
                .build();
    }
}
