package com.example.demo.awx.organization.service;

import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.mapper.AwxOrganizationMapper;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxOrganizationServiceImpl implements AwxOrganizationService {

    private final EntityManager entityManager;

    @Override
    public AwxOrganization handleCreate(AwxOrganizationCreateRequest request) {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setAwxId(request.getAwxId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        entityManager.persist(entity);

        return AwxOrganizationMapper.map(entity);
    }
}
