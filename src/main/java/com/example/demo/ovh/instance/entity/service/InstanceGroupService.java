package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.example.demo.ovh.instance.entity.mapper.InstanceGroupMapper;
import com.example.demo.ovh.instance.entity.model.InstanceGroup;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class InstanceGroupService implements IInstanceGroupService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public InstanceGroup handleCreated(InstanceGroupCreatedEvent event) {

        QProjectEntity qProject = QProjectEntity.projectEntity;

        ProjectEntity projectEntity = queryFactory.selectFrom(qProject)
                .where(qProject.id.eq(event.getProjectId()))
                .fetchOne();

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setId(event.getId());
        entity.setProjectEntity(projectEntity);
        entity.setOvhId(event.getOvhId());
        entity.setName(event.getName());
        entity.setType(event.getType());

        entityManager.persist(entity);

        return InstanceGroupMapper.map(entity);
    }
}
