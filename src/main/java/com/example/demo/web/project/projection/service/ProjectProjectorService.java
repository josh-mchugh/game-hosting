package com.example.demo.web.project.projection.service;

import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.web.project.projection.service.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.projection.service.model.FetchProjectDetailsResponse;
import com.example.demo.web.project.projection.service.projection.InstanceDetailsByIdProjection;
import com.example.demo.web.project.projection.service.projection.ProjectDetailsProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectProjectorService implements IProjectProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchProjectDetailsResponse getProjectDetails(FetchProjectDetailsQuery query) {

        ProjectDetailsProjection projectDetails = fetchProjectDetails(query.getId());
        InstanceDetailsByIdProjection instance = fetchInstanceDetails(query.getId());

        return FetchProjectDetailsResponse.builder()
                .name(projectDetails.getName())
                .gameType(projectDetails.getGameType())
                .instanceId(instance.getOvhId())
                .instanceStatus(instance.getInstanceStatus())
                .ip4Address(instance.getIpAddress())
                .build();
    }

    private ProjectDetailsProjection fetchProjectDetails(String projectId) {

        QProjectEntity qProject = QProjectEntity.projectEntity;

        return queryFactory.select(
                Projections.constructor(
                    ProjectDetailsProjection.class,
                    qProject.name,
                    qProject.gameEntity.type
                ))
                .from(qProject)
                .where(qProject.id.eq(projectId))
                .fetchOne();
    }

    private InstanceDetailsByIdProjection fetchInstanceDetails(String projectId) {

        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        return queryFactory.select(
                Projections.constructor(
                    InstanceDetailsByIdProjection.class,
                    qInstance.ovhId,
                    qInstance.status,
                    qInstance.ip4Address
                ))
                .from(qInstance)
                .where(qInstance.instanceGroupEntity.projectEntity.id.eq(projectId))
                .fetchOne();
    }
}
