package com.example.demo.awx.organization.service;

import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.example.demo.awx.organization.mapper.AwxOrganizationMapper;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxOrganizationService implements IAwxOrganizationService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsAny() {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        long count = queryFactory.select(qAwxOrganization.id)
                .from(qAwxOrganization)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxOrganization handleOrganizationCreate(AwxOrganizationCreateRequest request) {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setOrganizationId(request.getOrganizationId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        entityManager.persist(entity);

        return AwxOrganizationMapper.map(entity);
    }
}
