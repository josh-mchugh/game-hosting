package com.example.demo.awx.project.projection;

import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.entity.QAwxProjectEntity;
import com.example.demo.awx.project.entity.mapper.AwxProjectMapper;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxProjectProjector implements IAwxProjectProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        long count = queryFactory.select(qAwxProject)
                .from(qAwxProject)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxProject getByProjectId(Long projectId) {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        AwxProjectEntity entity = queryFactory.select(qAwxProject)
                .from(qAwxProject)
                .where(qAwxProject.awxId.eq(projectId))
                .fetchOne();

        return AwxProjectMapper.map(entity);
    }
}
