package com.example.demo.ovh.region.entity.service;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.aggregate.event.RegionUpdatedEvent;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.ovh.region.entity.mapper.RegionMapper;
import com.example.demo.ovh.region.entity.model.Region;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Region handleCreated(RegionCreatedEvent event) {

        RegionEntity entity = new RegionEntity();
        entity.setId(event.getId());
        entity.setName(event.getName());
        entity.setContinentCode(event.getContinentCode());
        entity.setCountryCodes(event.getCountryCodes());
        entity.setDataCenterLocation(event.getDataCenterLocation());
        entity.setStatus(event.getStatus());

        entityManager.persist(entity);

        return RegionMapper.map(entity);
    }

    @Override
    @EventHandler
    public Region handleUpdated(RegionUpdatedEvent event) {

        RegionEntity entity = getRegionById(event.getId());
        entity.setContinentCode(event.getContinentCode());
        entity.setCountryCodes(event.getCountryCodes());
        entity.setDataCenterLocation(event.getDataCenterLocation());
        entity.setStatus(event.getStatus());

        entityManager.persist(entity);

        return RegionMapper.map(entity);
    }

    private RegionEntity getRegionById(UUID id) {

        return getRegionById(id.toString());
    }

    private RegionEntity getRegionById(String id) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        return queryFactory.select(qRegion)
                .from(qRegion)
                .where(qRegion.id.eq(id))
                .fetchOne();
    }
}
