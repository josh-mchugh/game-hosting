package com.example.demo.web.project.service;

import com.example.demo.awx.feign.host.HostClient;
import com.example.demo.awx.feign.host.model.HostApi;
import com.example.demo.awx.feign.host.model.HostPatchApi;
import com.example.demo.awx.host.entity.QAwxHostEntity;
import com.example.demo.awx.host.model.AwxHost;
import com.example.demo.awx.host.service.IAwxHostService;
import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.feign.instance.InstanceClient;
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

    private final OvhConfig ovhConfig;
    private final JPQLQueryFactory queryFactory;
    private final InstanceClient instanceClient;
    private final IAwxHostService awxHostService;
    private final HostClient hostClient;

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

        instanceClient.startInstance(ovhConfig.getProjectId(), request.getInstanceId());

        Long hostId = getHostIdByInstanceId(request.getInstanceId());

        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(true)
                .build();

        HostApi hostApi = hostClient.updateHost(hostId, updateBody);

        AwxHost awxHost = awxHostService.handleEnabledRequest(hostId);
    }

    @Override
    public void handleProjectInstanceStop(ProjectInstanceStopRequest request) {

        instanceClient.stopInstance(ovhConfig.getProjectId(), request.getInstanceId());

        Long hostId = getHostIdByInstanceId(request.getInstanceId());

        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(false)
                .build();

        HostApi hostApi = hostClient.updateHost(hostId, updateBody);

        AwxHost awxHost = awxHostService.handleDisableRequest(hostId);
    }

    private Long getHostIdByInstanceId(String instanceId) {

        QAwxHostEntity qAwxHost = QAwxHostEntity.awxHostEntity;
        QInstanceEntity qInstance = QInstanceEntity.instanceEntity;

        return queryFactory.select(qAwxHost.hostId)
                .from(qAwxHost)
                .innerJoin(qAwxHost.instanceEntity, qInstance)
                .where(qInstance.instanceId.eq(instanceId))
                .fetchOne();
    }
}
