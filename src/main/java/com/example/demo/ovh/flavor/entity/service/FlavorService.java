package com.example.demo.ovh.flavor.entity.service;

import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.aggregate.event.FlavorUpdatedEvent;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.entity.mapper.FlavorMapper;
import com.example.demo.ovh.flavor.entity.model.Flavor;
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
public class FlavorService implements IFlavorService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Flavor handleCreated(FlavorCreatedEvent event) {

        FlavorEntity entity = new FlavorEntity();
        entity.setId(event.getId());
        entity.setRegionEntity(getRegionById(event.getRegionId()));
        entity.setFlavorId(event.getFlavorId());
        entity.setName(event.getName());
        entity.setType(event.getType());
        entity.setAvailable(event.getAvailable());
        entity.setHourly(event.getHourly());
        entity.setMonthly(event.getMonthly());
        entity.setQuota(event.getQuota());
        entity.setOsType(event.getOsType());
        entity.setVcpus(event.getVcpus());
        entity.setRam(event.getRam());
        entity.setDisk(event.getDisk());
        entity.setInboundBandwidth(event.getInboundBandwidth());
        entity.setOutboundBandwidth(event.getOutboundBandwidth());

        entityManager.persist(entity);

        return FlavorMapper.map(entity);
    }

    @Override
    @EventHandler
    public Flavor handleUpdated(FlavorUpdatedEvent event) {

        FlavorEntity entity = getById(event.getId().toString());
        entity.setRegionEntity(getRegionById(event.getRegionId()));
        entity.setName(event.getName());
        entity.setType(event.getType());
        entity.setAvailable(event.getAvailable());
        entity.setHourly(event.getHourly());
        entity.setMonthly(event.getMonthly());
        entity.setQuota(event.getQuota());
        entity.setOsType(event.getOsType());
        entity.setVcpus(event.getVcpus());
        entity.setRam(event.getRam());
        entity.setDisk(event.getDisk());
        entity.setInboundBandwidth(event.getInboundBandwidth());
        entity.setOutboundBandwidth(event.getOutboundBandwidth());

        entityManager.persist(entity);

        return FlavorMapper.map(entity);
    }

    private FlavorEntity getById(String id) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        return queryFactory.selectFrom(qFlavor)
                .where(qFlavor.id.eq(id))
                .fetchOne();
    }

    private RegionEntity getRegionById(String id) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        return queryFactory.select(qRegion)
                .from(qRegion)
                .where(qRegion.id.eq(id))
                .fetchOne();
    }
}
