package com.example.demo.ovh.instance.entity.mapper;

import com.example.demo.ovh.instance.entity.InstanceEntity;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

public class InstanceMapper {

    public static Instance map(InstanceEntity entity) {

        if (entity == null) {

            return null;
        }

        return Instance.builder()
                .id(entity.getId())
                .instanceId(entity.getInstanceId())
                .instanceCreatedDate(entity.getInstanceCreatedDate())
                .name(entity.getName())
                .ip4Address(entity.getIp4Address())
                .ip6Address(entity.getIp6Address())
                .status(entity.getStatus())
                .build();
    }

    public static ImmutableList<Instance> map(Collection<InstanceEntity> entities) {

        if(CollectionUtils.isEmpty(entities)) {

            return null;
        }

        return entities.stream()
                .map(InstanceMapper::map)
                .collect(ImmutableList.toImmutableList());
    }
}
