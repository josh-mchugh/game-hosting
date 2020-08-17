package com.example.demo.ovh.region.service;

import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.ovh.region.mapper.RegionMapper;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.ovh.region.service.model.RegionUpdateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class RegionService implements IRegionService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsAny() {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        long count = queryFactory.select(qRegion.id)
                .from(qRegion)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsByName(String name) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        long count = queryFactory.select(qRegion.id)
                .from(qRegion)
                .where(qRegion.name.eq(name))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public Region handleRegionCreate(RegionCreateRequest request) {

        RegionEntity entity = new RegionEntity();
        entity.setName(request.getName());
        entity.setContinentCode(request.getContinentCode());
        entity.setCountryCodes(request.getCountryCodes());
        entity.setDataCenterLocation(request.getDataCenterLocation());
        entity.setStatus(request.getStatus());

        entityManager.persist(entity);

        return RegionMapper.map(entity);
    }

    @Override
    public Region handleRegionUpdate(RegionUpdateRequest request) {

        RegionEntity entity = getRegionByName(request.getName());
        entity.setContinentCode(request.getContinentCode());
        entity.setCountryCodes(request.getCountryCodes());
        entity.setDataCenterLocation(request.getDataCenterLocation());
        entity.setStatus(request.getStatus());

        entityManager.persist(entity);

        return RegionMapper.map(entity);
    }

    private RegionEntity getRegionByName(String name) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        return queryFactory.selectFrom(qRegion)
                .where(qRegion.name.eq(name))
                .fetchOne();
    }
}
