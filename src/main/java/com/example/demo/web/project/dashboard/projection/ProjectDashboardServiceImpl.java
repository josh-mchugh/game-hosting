package com.example.demo.web.project.dashboard.projection;

import com.example.demo.awx.host.entity.QAwxHostEntity;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsResponse;
import com.example.demo.web.project.dashboard.projection.projection.AwxHostProjection;
import com.example.demo.web.project.dashboard.projection.projection.InstanceDetailsByIdProjection;
import com.example.demo.web.project.dashboard.projection.projection.ProjectDetailsProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectDashboardServiceImpl implements ProjectDashboardService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchProjectDetailsResponse getProjectDetails(FetchProjectDetailsQuery query) {

        FetchProjectDetailsResponse.Builder builder = FetchProjectDetailsResponse.builder();

        ProjectDetailsProjection projectDetails = fetchProjectDetails(query.getId());
        builder.name(projectDetails.getName());
        builder.gameType(projectDetails.getGameType());
        builder.status(projectDetails.getStatus());
        builder.state(projectDetails.getState());

        InstanceDetailsByIdProjection instance = fetchInstanceDetails(query.getId());
        if(instance != null) {

             builder.instanceId(instance.getOvhId());
             builder.instanceStatus(instance.getInstanceStatus());
             builder.ip4Address(instance.getIpAddress());
        }

        return builder.build();
    }

    private ProjectDetailsProjection fetchProjectDetails(String projectId) {

        QProjectEntity qProject = QProjectEntity.projectEntity;

        return queryFactory.select(
                Projections.constructor(
                    ProjectDetailsProjection.class,
                    qProject.name,
                    qProject.gameEntity.type,
                    qProject.status,
                    qProject.state
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

    @Override
    @QueryHandler
    public FetchAwxHostByInstanceOvhIdResponse fetchAwxHostByInstanceId(FetchAwxHostByInstanceOvhIdQuery query) {

        QAwxHostEntity qAwxHost = QAwxHostEntity.awxHostEntity;
        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        AwxHostProjection projection = queryFactory.select(Projections.constructor(
                    AwxHostProjection.class,
                    qAwxHost.id,
                    qAwxHost.awxId
                ))
                .from(qAwxHost)
                .innerJoin(qAwxHost.instanceEntity, qInstance)
                .where(qInstance.ovhId.eq(query.getInstanceOvhId()))
                .fetchOne();

        return new FetchAwxHostByInstanceOvhIdResponse(projection);
    }
}
