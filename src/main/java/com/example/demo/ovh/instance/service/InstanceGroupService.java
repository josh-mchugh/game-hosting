package com.example.demo.ovh.instance.service;

import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.example.demo.ovh.instance.mapper.InstanceGroupMapper;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public InstanceGroup handleInstanceGroupCreate(InstanceGroupCreateRequest request) {

        QProjectEntity qProject = QProjectEntity.projectEntity;

        ProjectEntity projectEntity = queryFactory.selectFrom(qProject)
                .where(qProject.id.eq(request.getProjectId()))
                .fetchOne();

        InstanceGroupEntity entity = new InstanceGroupEntity();
        entity.setGroupId(request.getInstanceGroupId());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setProjectEntity(projectEntity);

        entityManager.persist(entity);

        return InstanceGroupMapper.map(entity);
    }
}
