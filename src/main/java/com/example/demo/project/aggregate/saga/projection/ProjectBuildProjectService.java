package com.example.demo.project.aggregate.saga.projection;

import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.QCredentialEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.instance.entity.QInstanceGroupEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialResponse;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsResponse;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdResponse;
import com.example.demo.project.aggregate.saga.projection.project.ProjectCreateInstanceDetailsProjection;
import com.example.demo.project.aggregate.saga.projection.project.ProjectCredentialIdsProjection;
import com.example.demo.project.entity.QProjectEntity;
import com.querydsl.core.types.Projections;
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

    @Override
    @QueryHandler
    public FetchProjectCreateInstanceDetailsResponse fetchProjectCreateInstanceDetails(FetchProjectCreateInstanceDetailsQuery query) {

        QProjectEntity qProject = QProjectEntity.projectEntity;
        QRegionEntity qRegion = QRegionEntity.regionEntity;
        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;
        QImageEntity qImage = QImageEntity.imageEntity;
        QInstanceGroupEntity qInstanceGroup = QInstanceGroupEntity.instanceGroupEntity;

        ProjectCreateInstanceDetailsProjection projection = queryFactory.select(Projections.constructor(
                    ProjectCreateInstanceDetailsProjection.class,
                        qRegion.name,
                        qFlavor.id,
                        qFlavor.ovhId,
                        qImage.id,
                        qImage.ovhId,
                        qInstanceGroup.id,
                        qInstanceGroup.ovhId
                ))
                .from(qProject)
                .innerJoin(qProject.regionEntity, qRegion)
                .innerJoin(qProject.flavorEntity, qFlavor)
                .innerJoin(qProject.imageEntity, qImage)
                .innerJoin(qProject.instanceGroupEntity, qInstanceGroup)
                .where(qProject.id.eq(query.getId().toString()))
                .fetchOne();

        return FetchProjectCreateInstanceDetailsResponse.builder()
                .regionName(projection.getRegionName())
                .flavorId(projection.getFlavorId())
                .flavorOvhId(projection.getFlavorOvhId())
                .imageId(projection.getImageId())
                .imageOvhId(projection.getImageOvhId())
                .instanceGroupId(projection.getInstanceGroupId())
                .instanceGroupOvhId(projection.getInstanceGroupOvhId())
                .build();
    }

    @Override
    @QueryHandler
    public FetchAnsibleCredentialResponse fetchAnsibleCredential(FetchAnsibleCredentialQuery query) {

        QCredentialEntity qCredential = QCredentialEntity.credentialEntity;

        ProjectCredentialIdsProjection projection = queryFactory.select(
                    Projections.constructor(
                        ProjectCredentialIdsProjection.class,
                        qCredential.id,
                        qCredential.ovhId
                ))
                .from(qCredential)
                .where(qCredential.type.eq(CredentialType.ANSIBLE))
                .fetchOne();

        return projection != null ? new FetchAnsibleCredentialResponse(projection.getId(), projection.getOvhId()) : null;
    }
}
