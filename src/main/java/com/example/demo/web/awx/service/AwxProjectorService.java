package com.example.demo.web.awx.service;

import com.example.demo.awx.playbook.entity.QAwxPlayBookEntity;
import com.example.demo.awx.project.entity.QAwxProjectEntity;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksQuery;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksResponse;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdQuery;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdResponse;
import com.example.demo.web.awx.service.projection.ProjectProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxProjectorService implements IAwxProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    public ExistsAnyPlaybooksResponse existsAnyPlaybooks(ExistsAnyPlaybooksQuery query) {

        QAwxPlayBookEntity qAwxPlayBook = QAwxPlayBookEntity.awxPlayBookEntity;

        long count = queryFactory.select(qAwxPlayBook)
                .from(qAwxPlayBook)
                .fetchCount();

        return new ExistsAnyPlaybooksResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchProjectByAwxIdResponse getProjectByAwxId(FetchProjectByAwxIdQuery query) {

        QAwxProjectEntity qAwxProject = QAwxProjectEntity.awxProjectEntity;

        ProjectProjection projection = queryFactory.select(Projections.constructor(
                    ProjectProjection.class,
                    qAwxProject.id,
                    qAwxProject.awxId
                ))
                .from(qAwxProject)
                .where(qAwxProject.awxId.eq(query.getAwxId()))
                .fetchOne();

        return new FetchProjectByAwxIdResponse(projection);
    }
}
