package com.example.demo.awx.inventory.entity.mapper;

import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.model.AwxInventory;

public class AwxInventoryMapper {

    public static AwxInventory map(AwxInventoryEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxInventory.builder()
                .id(entity.getUUID())
                .awxId(entity.getAwxId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
