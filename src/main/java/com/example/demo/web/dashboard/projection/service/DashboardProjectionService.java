package com.example.demo.web.dashboard.projection.service;

import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.project.entity.QProjectMembershipEntity;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.VerificationStatus;
import com.example.demo.web.dashboard.projection.service.model.FetchDashboardDetailsQuery;
import com.example.demo.web.dashboard.projection.service.model.FetchDashboardDetailsResponse;
import com.example.demo.web.dashboard.projection.service.projection.ProjectDashboardProjection;
import com.example.demo.web.dashboard.projection.service.projection.UserDashboardProjection;
import com.google.common.collect.ImmutableList;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardProjectionService implements IDashboardProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchDashboardDetailsResponse fetchUserDashboard(FetchDashboardDetailsQuery query) {

        UserDashboardProjection userDashboardDetails = getUserDashboardProjection(query.getEmail());

        FetchDashboardDetailsResponse.Builder builder = FetchDashboardDetailsResponse.builder()
                .emailVerified(userDashboardDetails.isEmailValidated())
                .hasProjects(userDashboardDetails.isProjects());

        if(userDashboardDetails.isProjects()) {

            builder.projects(getProjectDashboardProjection(query.getEmail()));
        }

        return builder.build();
    }

    private UserDashboardProjection getUserDashboardProjection(String email) {

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

        return queryFactory.select(
                Projections.constructor(
                    UserDashboardProjection.class,
                    isVerified,
                    hasProjects
                ))
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();
    }

    public ImmutableList<ProjectDashboardProjection> getProjectDashboardProjection(String email) {

        QUserEntity qUser = QUserEntity.userEntity;
        QProjectMembershipEntity qProjectMembership = QProjectMembershipEntity.projectMembershipEntity;
        QProjectEntity qProject = QProjectEntity.projectEntity;

        return queryFactory.select(
                Projections.constructor(
                    ProjectDashboardProjection.class,
                    qProject.id,
                    qProject.name,
                    qProject.gameEntity.type
                ))
                .from(qProject)
                .innerJoin(qProject.projectMembershipsEntities, qProjectMembership)
                .innerJoin(qProjectMembership.userEntity, qUser)
                .where(qUser.email.eq(email))
                .orderBy(qProject.createdDate.asc())
                .fetch()
                .stream()
                .collect(ImmutableList.toImmutableList());
    }
}
