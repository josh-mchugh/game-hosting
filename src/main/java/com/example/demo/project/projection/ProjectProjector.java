package com.example.demo.project.projection;

import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.project.entity.QProjectMembershipEntity;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdProjection;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdQuery;
import com.example.demo.project.projection.model.ProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardQuery;
import com.example.demo.user.entity.QUserEntity;
import com.google.common.collect.ImmutableList;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectProjector implements IProjectProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public FetchProjectDashboardProjection fetchProjectDashboard(FetchProjectDashboardQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;
        QProjectMembershipEntity qProjectMembership = QProjectMembershipEntity.projectMembershipEntity;
        QProjectEntity qProject = QProjectEntity.projectEntity;

        ImmutableList<ProjectDashboardProjection> projects = queryFactory.select(
                Projections.constructor(ProjectDashboardProjection.class,
                        qProject.id,
                        qProject.name,
                        qProject.gameEntity.type
                ))
                .from(qProject)
                .innerJoin(qProject.projectMembershipsEntities, qProjectMembership)
                .innerJoin(qProjectMembership.userEntity, qUser)
                .where(qUser.email.eq(query.getEmail()))
                .orderBy(qProject.createdDate.asc())
                .fetch()
                .stream()
                .collect(ImmutableList.toImmutableList());

        return new FetchProjectDashboardProjection(projects);
    }

    @Override
    public FetchProjectDetailsByIdProjection fetchProjectDetails(FetchProjectDetailsByIdQuery query) {

        QProjectEntity qProject = QProjectEntity.projectEntity;

        return queryFactory.select(
                Projections.constructor(FetchProjectDetailsByIdProjection.class,
                        qProject.name,
                        qProject.gameEntity.type
                ))
                .from(qProject)
                .where(qProject.id.eq(query.getId()))
                .fetchOne();
    }
}
