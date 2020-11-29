package com.example.demo.ovh.instance.entity.mapper;

import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.example.demo.ovh.instance.entity.model.InstanceGroup;

public class InstanceGroupMapper {

    public static InstanceGroup map(InstanceGroupEntity entity) {

        if (entity == null) {

            return null;
        }

        return InstanceGroup.builder()
                .id(entity.getId())
                .groupId(entity.getGroupId())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }
}
