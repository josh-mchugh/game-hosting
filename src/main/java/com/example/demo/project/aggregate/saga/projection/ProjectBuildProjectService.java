package com.example.demo.project.aggregate.saga.projection;

import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdResponse;
import com.example.demo.project.entity.QProjectEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectBuildProjectService implements IProjectBuildProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchProjectRegionNameByIdResponse fetchProjectRegionNameById(FetchProjectRegionNameByIdQuery query) {

        QProjectEntity qProject = QProjectEntity.projectEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;

        String regionName = queryFactory.select(qRegion.name)
                .from(qProject)
                .innerJoin(qProject.regionEntity, qRegion)
                .where(qProject.id.eq(query.getId().toString()))
                .fetchOne();

        return new FetchProjectRegionNameByIdResponse(regionName);
    }
}
