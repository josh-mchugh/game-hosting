package com.example.demo.awx.organization.mapper;

import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.model.AwxOrganization;

public class AwxOrganizationMapper {

    public static AwxOrganization map(AwxOrganizationEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxOrganization.builder()
                .id(entity.getId())
                .organizationId(entity.getOrganizationId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
