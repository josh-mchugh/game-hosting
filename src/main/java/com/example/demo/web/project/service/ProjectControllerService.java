package com.example.demo.web.project.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.web.project.service.model.ProjectDetails;
import com.example.demo.web.project.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.service.model.ProjectInstanceStopRequest;
import com.example.demo.web.project.service.projections.InstanceDetailsProjection;
import com.example.demo.web.project.service.projections.ProjectDetailsProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectControllerService implements IProjectControllerService {

    private final AppConfig appConfig;
    private final JPQLQueryFactory queryFactory;
    private final OvhClient ovhClient;

    @Override
    public ProjectDetails getProjectDetails(String id) {

        QProjectEntity qProject = QProjectEntity.projectEntity;
        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        ProjectDetailsProjection project = queryFactory.select(
                Projections.constructor(ProjectDetailsProjection.class,
                        qProject.name,
                        qProject.gameEntity.type
                ))
                .from(qProject)
                .where(qProject.id.eq(id))
                .fetchOne();

        InstanceDetailsProjection instance = queryFactory.select(
                Projections.constructor(InstanceDetailsProjection.class,
                        qInstance.instanceId,
                        qInstance.status,
                        qInstance.ip4Address
                ))
                .from(qInstance)
                .where(qInstance.instanceGroupEntity.projectEntity.id.eq(id))
                .fetchOne();

        return ProjectDetails.builder()
                .name(project.getName())
                .gameType(project.getGameType())
                .instanceId(instance.getId())
                .instanceStatus(instance.getInstanceStatus())
                .ip4Address(instance.getIpAddress())
                .build();
    }

    @Override
    public void handleProjectInstanceStart(ProjectInstanceStartRequest request) {

        ovhClient.startInstance(appConfig.getOvh().getProjectId(), request.getInstanceId());
    }

    @Override
    public void handleProjectInstanceStop(ProjectInstanceStopRequest request) {

        ovhClient.stopInstance(appConfig.getOvh().getProjectId(), request.getInstanceId());
    }
}
