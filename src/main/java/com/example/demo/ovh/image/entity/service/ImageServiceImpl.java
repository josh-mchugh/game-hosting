package com.example.demo.ovh.image.entity.service;

import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.aggregate.event.ImageUpdatedEvent;
import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.image.entity.mapper.ImageMapper;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Image handleCreated(ImageCreatedEvent event) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        RegionEntity regionEntity = queryFactory.selectFrom(qRegion)
                .where(qRegion.id.eq(event.getRegionId().toString()))
                .fetchOne();

        ImageEntity entity = new ImageEntity();
        entity.setId(event.getId());
        entity.setOvhId(event.getOvhId());
        entity.setRegionEntity(regionEntity);
        entity.setName(event.getName());
        entity.setType(event.getType());
        entity.setImageCreatedDate(event.getImageCreatedDate());
        entity.setFlavorType(event.getFlavorType());
        entity.setHourly(event.getHourly());
        entity.setMonthly(event.getMonthly());
        entity.setSize(event.getSize());
        entity.setMinRam(event.getMinRam());
        entity.setMinDisk(event.getMinDisk());
        entity.setUsername(event.getUsername());
        entity.setStatus(event.getStatus());
        entity.setVisibility(event.getVisibility());

        entityManager.persist(entity);

        return ImageMapper.map(entity);
    }

    @Override
    @EventHandler
    public Image handleUpdated(ImageUpdatedEvent event) {

        ImageEntity entity = getById(event.getId().toString());
        entity.setOvhId(event.getOvhId());
        entity.setType(event.getType());
        entity.setImageCreatedDate(event.getImageCreatedDate());
        entity.setFlavorType(event.getFlavorType());
        entity.setHourly(event.getHourly());
        entity.setMonthly(event.getMonthly());
        entity.setSize(event.getSize());
        entity.setMinRam(event.getMinRam());
        entity.setMinDisk(event.getMinDisk());
        entity.setUsername(event.getUsername());
        entity.setStatus(event.getStatus());
        entity.setVisibility(event.getVisibility());

        entityManager.persist(entity);

        return ImageMapper.map(entity);
    }

    private ImageEntity getById(String id) {

        QImageEntity qImage = QImageEntity.imageEntity;

        return queryFactory.select(qImage)
                .from(qImage)
                .where(qImage.id.eq(id))
                .fetchOne();
    }
}
