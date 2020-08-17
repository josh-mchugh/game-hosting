package com.example.demo.ovh.image.mapper;

import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.model.Image;

public class ImageMapper {

    public static Image map(ImageEntity entity) {

        if (entity == null) {

            return null;
        }

        return Image.builder()
                .id(entity.getId())
                .imageId(entity.getImageId())
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
