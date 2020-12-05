package com.example.demo.web.dashboard.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.projection.IGameProjection;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.project.entity.QProjectMembershipEntity;
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

        Game game = gameProjection.getGameByType(request.getGameType());

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .gameId(game.getId())
                .userId(sessionUtil.getCurrentUser().getId())
                .build();

        UUID projectId = commandGateway.sendAndWait(command);

        return DashboardProjectCreateResponse.builder()
                .projectId(projectId.toString())
                .build();
    }
}
