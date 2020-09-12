package com.example.demo.ovh.image.service;

import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.image.mapper.ImageMapper;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.image.service.model.ImageUpdateRequest;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsAny() {

        QImageEntity qImage = QImageEntity.imageEntity;

        long count = queryFactory.select(qImage.id)
                .from(qImage)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsByName(String imageId) {

        QImageEntity qImage = QImageEntity.imageEntity;

        long count = queryFactory.select(qImage.id)
                .from(qImage)
                .where(qImage.name.eq(imageId))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public Image handleImageCreate(ImageCreateRequest request) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        RegionEntity regionEntity = queryFactory.selectFrom(qRegion)
                .where(qRegion.name.eq(request.getRegionName()))
                .fetchOne();

        ImageEntity entity = new ImageEntity();
        entity.setImageId(request.getImageId());
        entity.setRegionEntity(regionEntity);
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setImageCreatedDate(request.getImageCreatedDate());
        entity.setFlavorType(request.getFlavorType());
        entity.setHourly(request.getHourly());
        entity.setMonthly(request.getMonthly());
        entity.setSize(request.getSize());
        entity.setMinRam(request.getMinRam());
        entity.setMinDisk(request.getMinDisk());
        entity.setUsername(request.getUsername());
        entity.setStatus(request.getStatus());
        entity.setVisibility(request.getVisibility());

        entityManager.persist(entity);

        return ImageMapper.map(entity);
    }

    @Override
    public Image handleImageUpdate(ImageUpdateRequest request) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        RegionEntity regionEntity = queryFactory.selectFrom(qRegion)
                .where(qRegion.name.eq(request.getRegionName()))
                .fetchOne();

        ImageEntity entity = getByName(request.getName());
        entity.setImageId(request.getImageId());
        entity.setRegionEntity(regionEntity);
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setImageCreatedDate(request.getImageCreatedDate());
        entity.setFlavorType(request.getFlavorType());
        entity.setHourly(request.getHourly());
        entity.setMonthly(request.getMonthly());
        entity.setSize(request.getSize());
        entity.setMinRam(request.getMinRam());
        entity.setMinDisk(request.getMinDisk());
        entity.setUsername(request.getUsername());
        entity.setStatus(request.getStatus());
        entity.setVisibility(request.getVisibility());

        entityManager.persist(entity);

        return ImageMapper.map(entity);
    }

    private ImageEntity getByName(String name) {

        QImageEntity qImage = QImageEntity.imageEntity;

        return queryFactory.selectFrom(qImage)
                .where(qImage.name.eq(name))
                .fetchOne();
    }
}
