package com.example.demo.awx.playbook.entity.service;

import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import com.example.demo.awx.playbook.entity.mapper.AwxPlaybookMapper;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;
import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.entity.QAwxProjectEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AwxPlaybookService implements IAwxPlaybookService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public AwxPlaybook handleAwxPlaybookCreated(AwxPlaybookCreatedEvent event) {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        AwxProjectEntity awxProjectEntity = queryFactory.select(qAwxProject)
                .from(qAwxProject)
                .where(qAwxProject.id.eq(event.getAwxProjectId()))
                .fetchOne();

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setId(event.getId());
        entity.setAwxProjectEntity(awxProjectEntity);
        entity.setName(event.getName());
        entity.setType(event.getType());

        entityManager.persist(entity);

        return AwxPlaybookMapper.map(entity);
    }
}
