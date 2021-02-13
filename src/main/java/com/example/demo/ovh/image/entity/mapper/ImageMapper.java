package com.example.demo.ovh.image.entity.mapper;

import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.model.Image;

public class ImageMapper {

    public static Image map(ImageEntity entity) {

        if (entity == null) {

            return null;
        }

        return Image.builder()
                .id(entity.getUUID())
                .ovhId(entity.getOvhId())
                .name(entity.getName())
                .type(entity.getType())
                .imageCreatedDate(entity.getImageCreatedDate())
                .flavorType(entity.getFlavorType())
                .hourly(entity.getHourly())
                .monthly(entity.getMonthly())
                .size(entity.getSize())
                .minRam(entity.getMinRam())
                .minDisk(entity.getMinDisk())
                .username(entity.getUsername())
                .status(entity.getStatus())
                .visibility(entity.getVisibility())
                .build();
    }
}
