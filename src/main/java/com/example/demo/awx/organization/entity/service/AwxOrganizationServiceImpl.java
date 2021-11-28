package com.example.demo.awx.organization.entity.service;

import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.awx.organization.entity.mapper.AwxOrganizationMapper;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxOrganizationServiceImpl implements AwxOrganizationService {

    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxOrganization handleCreated(AwxOrganizationCreatedEvent event) {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setId(event.getId());
        entity.setAwxId(event.getAwxId());
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());

        entityManager.persist(entity);

        return AwxOrganizationMapper.map(entity);
    }
}
