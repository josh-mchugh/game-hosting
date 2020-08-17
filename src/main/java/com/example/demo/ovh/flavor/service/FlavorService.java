package com.example.demo.ovh.flavor.service;

import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.flavor.mapper.FlavorMapper;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.flavor.service.model.FlavorUpdateRequest;
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
public class FlavorService implements IFlavorService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsAny() {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        long count = queryFactory.select(qFlavor.id)
                .from(qFlavor)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsByFlavorId(String flavorId) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        long count = queryFactory.selectFrom(qFlavor)
                .where(qFlavor.flavorId.eq(flavorId))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public Flavor handleFlavorCreate(FlavorCreateRequest request) {

        RegionEntity regionEntity = getRegionByName(request.getRegionName());

        FlavorEntity entity = new FlavorEntity();
        entity.setRegionEntity(regionEntity);
        entity.setFlavorId(request.getFlavorId());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setAvailable(request.getAvailable());
        entity.setHourly(request.getHourly());
        entity.setMonthly(request.getMonthly());
        entity.setQuota(request.getQuota());
        entity.setOsType(request.getOsType());
        entity.setVcpus(request.getVcpus());
        entity.setRam(request.getRam());
        entity.setDisk(request.getDisk());
        entity.setInboundBandwidth(request.getInboundBandwidth());
        entity.setOutboundBandwidth(request.getOutboundBandwidth());

        entityManager.persist(entity);

        return FlavorMapper.map(entity);
    }

    @Override
    public Flavor handleFlavorUpdate(FlavorUpdateRequest request) {

        FlavorEntity entity = getByFlavorId(request.getFlavorId());
        entity.setRegionEntity(getRegionByName(request.getRegionName()));
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setAvailable(request.getAvailable());
        entity.setHourly(request.getHourly());
        entity.setMonthly(request.getMonthly());
        entity.setQuota(request.getQuota());
        entity.setOsType(request.getOsType());
        entity.setVcpus(request.getVcpus());
        entity.setRam(request.getRam());
        entity.setDisk(request.getDisk());
        entity.setInboundBandwidth(request.getInboundBandwidth());
        entity.setOutboundBandwidth(request.getOutboundBandwidth());

        entityManager.persist(entity);

        return FlavorMapper.map(entity);
    }

    private FlavorEntity getByFlavorId(String flavorId) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        return queryFactory.selectFrom(qFlavor)
                .where(qFlavor.flavorId.eq(flavorId))
                .fetchOne();
    }

    private RegionEntity getRegionByName(String name) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        return queryFactory.selectFrom(qRegion)
                .where(qRegion.name.eq(name))
                .fetchOne();
    }
}
