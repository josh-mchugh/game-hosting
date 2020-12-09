package com.example.demo.web.project.service;

import com.example.demo.awx.host.aggregate.command.AwxHostDisableCommand;
import com.example.demo.awx.host.aggregate.command.AwxHostEnableCommand;
import com.example.demo.awx.host.feign.HostFeignService;
import com.example.demo.awx.host.feign.model.HostPatchApi;
import com.example.demo.awx.host.projection.IAwxHostProjector;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;
import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.entity.QInstanceEntity;
import com.example.demo.ovh.instance.feign.InstanceClient;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.web.project.service.model.ProjectDetails;
import com.example.demo.web.project.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.service.model.ProjectInstanceStopRequest;
import com.example.demo.web.project.service.projections.InstanceDetailsProjection;
import com.example.demo.web.project.service.projections.ProjectDetailsProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectControllerService implements IProjectControllerService {

    private final OvhConfig ovhConfig;
    private final JPQLQueryFactory queryFactory;
    private final InstanceClient instanceClient;
    private final IAwxHostProjector awxHostProjector;
    private final HostFeignService hostFeignService;
    private final CommandGateway commandGateway;

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

        // Call OVH to start instance
        instanceClient.startInstance(ovhConfig.getProjectId(), request.getInstanceId());

        // Get AwxHost by OvH Instance Id
        AwxHostAwxIdQuery query = new AwxHostAwxIdQuery(request.getInstanceId());
        AwxHostAwxIdProjection projection = awxHostProjector.getHostIdProjection(query);

        // Call AWX to set Host to enabled
        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(true)
                .build();
        hostFeignService.updateHost(projection.getAwxId(), updateBody);

        // Send command to enable AwxHost
        AwxHostEnableCommand command = new AwxHostEnableCommand(UUID.fromString(projection.getAwxHostId()));
        commandGateway.send(command);
    }

    @Override
    public void handleProjectInstanceStop(ProjectInstanceStopRequest request) {

        // Call OVH to stop instance
        instanceClient.stopInstance(ovhConfig.getProjectId(), request.getInstanceId());

        // Get AwxHost by OvH Instance Id
        AwxHostAwxIdQuery query = new AwxHostAwxIdQuery(request.getInstanceId());
        AwxHostAwxIdProjection projection = awxHostProjector.getHostIdProjection(query);

        // Call AWX to set Host to disabled
        HostPatchApi updateBody = HostPatchApi.builder()
                .enabled(false)
                .build();
        hostFeignService.updateHost(projection.getAwxId(), updateBody);

        // Send command to disable AwxHost
        AwxHostDisableCommand command = new AwxHostDisableCommand(UUID.fromString(projection.getAwxHostId()));
        commandGateway.send(command);
    }
}
