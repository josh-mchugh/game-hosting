package com.example.demo.awx.playbook.service;

import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import com.example.demo.awx.playbook.entity.PlaybookType;
import com.example.demo.awx.playbook.entity.QAwxPlayBookEntity;
import com.example.demo.awx.playbook.mapper.AwxPlaybookMapper;
import com.example.demo.awx.playbook.model.AwxPlaybook;
import com.example.demo.awx.playbook.service.model.AwxPlaybookCreateRequest;
import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.entity.QAwxProjectEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    public boolean existsAny() {

        QAwxPlayBookEntity qAwxPlayBook = QAwxPlayBookEntity.awxPlayBookEntity;

        long count = queryFactory.select(qAwxPlayBook)
                .from(qAwxPlayBook)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxPlaybook handleCreateRequest(AwxPlaybookCreateRequest request) {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        AwxProjectEntity awxProjectEntity = queryFactory.select(qAwxProject)
                .from(qAwxProject)
                .where(qAwxProject.projectId.eq(request.getProjectId()))
                .fetchOne();

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setAwxProjectEntity(awxProjectEntity);
        entity.setName(request.getName());
        entity.setType(parseType(request.getName()));

        entityManager.persist(entity);

        return AwxPlaybookMapper.map(entity);
    }

    private PlaybookType parseType(String name) {

        String value = StringUtils.substringBefore(name, "-playbook.yml");

        if(value.equals(name)) {

            throw new IllegalArgumentException("Unable to parse and convert playbook name to PlaybookType");
        }

        return PlaybookType.valueOf(value.replace("-", "_").toUpperCase());
    }
}
