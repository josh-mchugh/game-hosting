package com.example.demo.web.dashboard.service;

import com.example.demo.awx.host.aggregate.command.AwxHostCreateCommand;
import com.example.demo.awx.host.feign.HostClient;
import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.feign.model.HostCreateApi;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.inventory.projection.IAwxInventoryProjector;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.projection.IGameProjection;
import com.example.demo.ovh.credential.entity.service.ICredentialService;
import com.example.demo.ovh.credential.projector.ICredentialProjector;
import com.example.demo.ovh.feign.common.IpAddressApi;
import com.example.demo.ovh.feign.instance.InstanceClient;
import com.example.demo.ovh.feign.instance.InstanceGroupClient;
import com.example.demo.ovh.feign.instance.model.InstanceApi;
import com.example.demo.ovh.feign.instance.model.InstanceCreateApi;
import com.example.demo.ovh.feign.instance.model.InstanceGroupApi;
import com.example.demo.ovh.feign.instance.model.InstanceGroupCreateApi;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.IInstanceGroupService;
import com.example.demo.ovh.instance.service.IInstanceService;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceUpdateRequest;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.project.entity.QProjectMembershipEntity;
import com.example.demo.project.entity.model.Project;
import com.example.demo.project.entity.service.IProjectService;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.VerificationStatus;
import com.example.demo.web.dashboard.service.model.DashboardDetailsResponse;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateResponse;
import com.example.demo.web.dashboard.service.projections.DashboardDetailsProjection;
import com.example.demo.web.dashboard.service.projections.DashboardProjectProjection;
import com.google.common.collect.ImmutableList;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {

    private final ISessionUtil sessionUtil;
    private final IProjectService projectService;
    private final IInstanceGroupService instanceGroupService;
    private final IInstanceService instanceService;
    private final ICredentialService credentialService;
    private final ICredentialProjector credentialProjector;
    private final InstanceClient instanceClient;
    private final InstanceGroupClient instanceGroupClient;
    private final HostClient hostClient;
    private final IAwxInventoryProjector awxInventoryProjector;
    private final OvhConfig ovhConfig;
    private final AwxConfig awxConfig;
    private final IGameProjection gameProjection;
    private final JPQLQueryFactory queryFactory;
    private final CommandGateway commandGateway;

    @Override
    public DashboardDetailsResponse getDashboardDetails() {

        QUserEntity qUser = QUserEntity.userEntity;
        QProjectMembershipEntity qProjectMembership = QProjectMembershipEntity.projectMembershipEntity;
        QProjectEntity qProject = QProjectEntity.projectEntity;

        JPQLQuery<Long> projectCountQuery = JPAExpressions.select(qProject.id.count())
                .from(qProject)
                .innerJoin(qProject.projectMembershipsEntities, qProjectMembership)
                .where(qProjectMembership.userEntity.eq(qUser));

        BooleanExpression isVerified = new CaseBuilder()
                .when(qUser.verificationEntity.status.eq(VerificationStatus.VERIFIED))
                .then(true)
                .otherwise(false);

        BooleanExpression hasProjects = new CaseBuilder()
                .when(projectCountQuery.goe(1L))
                .then(true)
                .otherwise(false);

        DashboardDetailsProjection projection = queryFactory.select(
                Projections.constructor(DashboardDetailsProjection.class,
                        isVerified,
                        hasProjects
                ))
                .from(qUser)
                .where(qUser.email.eq(sessionUtil.getCurrentUserEmail()))
                .fetchOne();

        DashboardDetailsResponse.Builder builder = DashboardDetailsResponse.builder()
                .emailVerified(projection.isEmailValidated())
                .hasProjects(projection.isProjects());

        if(projection.isProjects()) {

            builder.projects(getDashboardProjects());
        }

        return builder.build();
    }

    private ImmutableList<DashboardProjectProjection> getDashboardProjects() {

        QUserEntity qUser = QUserEntity.userEntity;
        QProjectMembershipEntity qProjectMembership = QProjectMembershipEntity.projectMembershipEntity;
        QProjectEntity qProject = QProjectEntity.projectEntity;

        List<DashboardProjectProjection> projects = queryFactory.select(
                Projections.constructor(DashboardProjectProjection.class,
                        qProject.id,
                        qProject.name,
                        qProject.gameEntity.type
                ))
                .from(qProject)
                .innerJoin(qProject.projectMembershipsEntities, qProjectMembership)
                .innerJoin(qProjectMembership.userEntity, qUser)
                .where(qUser.email.eq(sessionUtil.getCurrentUserEmail()))
                .orderBy(qProject.createdDate.asc())
                .fetch();

        return ImmutableList.copyOf(projects);
    }

    @Override
    public DashboardProjectCreateResponse handleDashboardProjectCreate(DashboardProjectCreateRequest request) {

        //LocalDateTime startTime = LocalDateTime.now();
        //log.info("Entered handleDashboardProject: {}", startTime);

        log.info("Creating Project...");
        UUID projectId = handleProjectCreate(request);

        /*

        log.info("Calling OVH API to create Instance Group...");
        InstanceGroupApi groupResponse = handleInstanceGroupCreateApi(project, request);

        log.info("Creating Instance Group Entity...");
        InstanceGroup instanceGroup = handleInstanceGroupCreate(project, groupResponse);

        log.info("Get Ansible Credential...");
        String sshKeyId = credentialProjector.getAnsibleSshKeyId();

        log.info("Call OVH API to create new Instance...");
        InstanceApi instanceResponse = handleInstanceCreateApi(project, instanceGroup, request, sshKeyId);

        log.info("Creating Instance Entity...");
        Instance instance = handleInstanceCreate(instanceResponse, instanceGroup);

        log.info("Poll OVH Instance API until instance is active...");
        InstanceApi activeInstanceApi = pollForActiveInstance(instance.getInstanceId());

        log.info("Updating Instance entity...");
        instance = handleInstanceActiveUpdate(instance, activeInstanceApi);

        log.info("Retrieving AWX Inventory Information...");
        AwxInventory awxInventory = awxInventoryProjector.findByName(awxConfig.getInventory().getName());

        log.info("Calling AWX Host to create awx host....");
        HostApi hostApi = createHostApi(awxInventory, instance);

        log.info("Creating AWX Host entity...");
        createAwxHost(awxInventory, instance, hostApi);

        log.info("Finished handleDashboardProjectCreate. Total Time: {} seconds", ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()));

        */

        return DashboardProjectCreateResponse.builder()
                .projectId(projectId.toString())
                .build();
    }

    private UUID handleProjectCreate(DashboardProjectCreateRequest request) {

        Game game = gameProjection.getGameByType(request.getGameType());

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .gameId(game.getId())
                .userId(sessionUtil.getCurrentUser().getId())
                .build();

        return commandGateway.sendAndWait(command);
    }

    private InstanceGroupApi handleInstanceGroupCreateApi(Project project, DashboardProjectCreateRequest request) {

        InstanceGroupCreateApi groupCreateRequest = InstanceGroupCreateApi.builder()
                .name(project.getId())
                .region(request.getRegion())
                .type("affinity")
                .build();

        return instanceGroupClient.createInstanceGroup(ovhConfig.getProjectId(), groupCreateRequest);
    }

    private InstanceGroup handleInstanceGroupCreate(Project project, InstanceGroupApi groupResponse) {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId(groupResponse.getId())
                .projectId(project.getId())
                .name(groupResponse.getName())
                .type(groupResponse.getType())
                .build();

        return instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);
    }

    private InstanceApi handleInstanceCreateApi(Project project, InstanceGroup instanceGroup, DashboardProjectCreateRequest request, String sshKeyId) {

        // TODO: Remove hard coded image id
        InstanceCreateApi ovhInstanceCreateRequest = InstanceCreateApi.builder()
                .name(project.getId())
                .flavorId(request.getFlavor())
                .imageId("22772a1f-9fc1-4e4d-9567-fc3e0d582824")
                .region(request.getRegion())
                .groupId(instanceGroup.getGroupId())
                .sshKeyId(sshKeyId)
                .build();

        return instanceClient.createInstance(ovhConfig.getProjectId(), ovhInstanceCreateRequest);
    }

    private Instance handleInstanceCreate(InstanceApi instanceApi, InstanceGroup instanceGroup) {

        InstanceCreateRequest instanceCreateRequest = InstanceCreateRequest.builder()
                .instanceId(instanceApi.getId())
                .flavorId(instanceApi.getFlavor().getFlavorId())
                .imageId(instanceApi.getImage().getImageId())
                .groupId(instanceGroup.getGroupId())
                .sshKeyId(instanceApi.getSshKey().getId())
                .instanceCreatedDate(instanceApi.getCreatedDate())
                .name(instanceApi.getName())
                .status(instanceApi.getStatus())
                .build();

        return instanceService.handleInstanceCreate(instanceCreateRequest);
    }

    @SneakyThrows
    public InstanceApi pollForActiveInstance(String instanceId) {

        int count = 0;

        log.info("Calling OVH API to get Instance status");
        InstanceApi instanceApi =  instanceClient.getInstanceById(ovhConfig.getProjectId(), instanceId);

        while (!instanceApi.getStatus().equals(InstanceStatus.ACTIVE)) {

            if(count >= 20) {

                log.info("Exiting polling as max attempts for instance API exceeded.");

                break;
            }

            Thread.sleep(1_500);

            log.info("Calling OVH API to get Instance status. Attempt: {}", count);
            instanceApi = instanceClient.getInstanceById(ovhConfig.getProjectId(), instanceId);

            count++;
        }

        log.info("Returning updated Instance API...");

        return instanceApi;
    }

    private Instance handleInstanceActiveUpdate(Instance instance, InstanceApi instanceApi) {

        InstanceUpdateRequest request = InstanceUpdateRequest.builder()
                .id(instance.getId())
                .name(instance.getName())
                .status(instanceApi.getStatus())
                .instanceCreatedDate(instance.getInstanceCreatedDate())
                .ip4Address(getIpAddress(instanceApi.getIpAddresses(), 4))
                .ip6Address(getIpAddress(instanceApi.getIpAddresses(), 6))
                .build();

        return instanceService.handleInstanceUpdate(request);
    }

    private String getIpAddress(List<IpAddressApi> ipAddresses, Integer version) {

        if(ipAddresses == null) {

            return null;
        }

        return ipAddresses.stream()
                .filter(ipAddress -> ipAddress.getVersion().equals(version))
                .map(IpAddressApi::getIp)
                .findFirst()
                .orElse(null);
    }

    private HostApi createHostApi(AwxInventory awxInventory, Instance instance) {

        HostCreateApi hostCreateApi = HostCreateApi.builder()
                .inventoryId(awxInventory.getInventoryId())
                .name(instance.getIp4Address())
                .description(instance.getId())
                .enabled(true)
                .build();

        return hostClient.createHost(hostCreateApi);
    }

    private void createAwxHost(AwxInventory awxInventory, Instance instance, HostApi hostApi) {

        AwxHostCreateCommand command = AwxHostCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(awxInventory.getId())
                .instanceId(instance.getId())
                .hostId(hostApi.getId())
                .hostname(hostApi.getName())
                .description(hostApi.getDescription())
                .enabled(hostApi.getEnabled())
                .build();

        commandGateway.send(command);
    }
}
