package com.example.demo.project.entity.service;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.aggregate.event.ProjectFlavorAddedEvent;
import com.example.demo.project.aggregate.event.ProjectRegionAddedEvent;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.entity.ProjectMembershipEntity;
import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.project.entity.mapper.ProjectMapper;
import com.example.demo.project.entity.model.Project;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserEntity;
import com.google.common.collect.Lists;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class ProjectService implements IProjectService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Project handleCreated(ProjectCreatedEvent event) {

        QGameEntity qGame = QGameEntity.gameEntity;
        QUserEntity qUser = QUserEntity.userEntity;

        GameEntity gameEntity = queryFactory.select(qGame)
                .from(qGame)
                .where(qGame.id.eq(event.getGameId().toString()))
                .fetchOne();

        UserEntity userEntity = queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(event.getMember().getUserId().toString()))
                .fetchOne();

        ProjectEntity entity = new ProjectEntity();
        entity.setId(event.getId());
        entity.setName(event.getName());
        entity.setStatus(event.getStatus());
        entity.setState(event.getState());
        entity.setGameEntity(gameEntity);

        ProjectMembershipEntity projectMembershipEntity = new ProjectMembershipEntity();
        projectMembershipEntity.setId(event.getMember().getId());
        projectMembershipEntity.setProjectEntity(entity);
        projectMembershipEntity.setUserEntity(userEntity);
        projectMembershipEntity.setRole(event.getMember().getRole());

        entity.setProjectMembershipsEntities(Lists.newArrayList(projectMembershipEntity));

        entityManager.persist(entity);

        return ProjectMapper.map(entity);
    }

    @Override
    @EventHandler
    public Project handleRegionAdded(ProjectRegionAddedEvent event) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        RegionEntity regionEntity = queryFactory.select(qRegion)
                .from(qRegion)
                .where(qRegion.id.eq(event.getOvhRegionId().toString()))
                .fetchOne();

        ProjectEntity entity = getProjectById(event.getId());
        entity.setRegionEntity(regionEntity);
        entity.setState(event.getState());

        entityManager.persist(entity);

        return ProjectMapper.map(entity);
    }

    @Override
    @EventHandler
    public Project handleFlavorAdded(ProjectFlavorAddedEvent event) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        FlavorEntity flavorEntity = queryFactory.select(qFlavor)
                .from(qFlavor)
                .where(qFlavor.id.eq(event.getOvhFlavorId().toString()))
                .fetchOne();

        ProjectEntity entity = getProjectById(event.getId());
        entity.setFlavorEntity(flavorEntity);
        entity.setState(event.getState());

        entityManager.persist(entity);

        return ProjectMapper.map(entity);
    }

    private ProjectEntity getProjectById(UUID id) {

        QProjectEntity qProject = QProjectEntity.projectEntity;

        return queryFactory.select(qProject)
                .from(qProject)
                .where(qProject.id.eq(id.toString()))
                .fetchOne();
    }
}
