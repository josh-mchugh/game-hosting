package com.example.demo.web.dashboard.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.feign.instance.InstanceClient;
import com.example.demo.ovh.feign.instance.InstanceGroupClient;
import com.example.demo.ovh.feign.instance.model.InstanceApi;
import com.example.demo.ovh.feign.instance.model.InstanceCreateApi;
import com.example.demo.ovh.feign.instance.model.InstanceGroupApi;
import com.example.demo.ovh.feign.instance.model.InstanceGroupCreateApi;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.IInstanceGroupService;
import com.example.demo.ovh.instance.service.IInstanceService;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.project.entity.QProjectMembershipEntity;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
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
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {

    private final ISessionUtil sessionUtil;
    private final IProjectService projectService;
    private final IInstanceGroupService instanceGroupService;
    private final IInstanceService instanceService;
    private final ICredentialService credentialService;
    private final InstanceClient instanceClient;
    private final InstanceGroupClient instanceGroupClient;
    private final AppConfig appConfig;
    private final JPQLQueryFactory queryFactory;

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

        // Create Project Entity
        Project project = handleProjectCreate(request);

        // Call OVH to create Instance Group
        InstanceGroupApi groupResponse = handleInstanceGroupCreateApi(project, request);

        // Create Instance Group Entity
        InstanceGroup instanceGroup = handleInstanceGroupCreate(project, groupResponse);

        // Get Ansible Credential
        String sshKeyId = credentialService.getAnsibleCredentialSshKeyId();

        // Call OVH to create Instance
        InstanceApi instanceResponse = handleInstanceCreateApi(project, instanceGroup, request, sshKeyId);

        // Create Instance Entity
        Instance instance = handleInstanceCreate(instanceResponse, instanceGroup);

        return DashboardProjectCreateResponse.builder()
                .projectId(project.getId())
                .build();
    }

    private Project handleProjectCreate(DashboardProjectCreateRequest request) {

        ProjectCreateRequest projectCreateRequest = ProjectCreateRequest.builder()
                .name(request.getName())
                .userId(sessionUtil.getCurrentUser().getId())
                .gameType(request.getGameType())
                .build();

        return projectService.handleProjectCreate(projectCreateRequest);
    }

    private InstanceGroupApi handleInstanceGroupCreateApi(Project project, DashboardProjectCreateRequest request) {

        InstanceGroupCreateApi groupCreateRequest = InstanceGroupCreateApi.builder()
                .name(project.getId())
                .region(request.getRegion())
                .type("affinity")
                .build();

        return instanceGroupClient.createInstanceGroup(appConfig.getOvh().getProjectId(), groupCreateRequest);
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

        InstanceCreateApi ovhInstanceCreateRequest = InstanceCreateApi.builder()
                .name(project.getName())
                .flavorId(request.getFlavor())
                .imageId("cefc8220-ba0a-4327-b13d-591abaf4be0c")
                .region(request.getRegion())
                .groupId(instanceGroup.getGroupId())
                .sshKeyId(sshKeyId)
                .build();

        return instanceClient.createInstance(appConfig.getOvh().getProjectId(), ovhInstanceCreateRequest);
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
}
