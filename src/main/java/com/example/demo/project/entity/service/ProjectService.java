package com.example.demo.project.entity.service;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.entity.ProjectMembershipEntity;
import com.example.demo.project.entity.ProjectMembershipRole;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
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
        entity.setStatus(ProjectStatus.ACTIVE);
        entity.setState(ProjectState.BUILD);
        entity.setGameEntity(gameEntity);

        ProjectMembershipEntity projectMembershipEntity = new ProjectMembershipEntity();
        projectMembershipEntity.setId(event.getMember().getId());
        projectMembershipEntity.setProjectEntity(entity);
        projectMembershipEntity.setUserEntity(userEntity);
        projectMembershipEntity.setRole(ProjectMembershipRole.OWNER);

        entity.setProjectMembershipsEntities(Lists.newArrayList(projectMembershipEntity));

        entityManager.persist(entity);

        return ProjectMapper.map(entity);
    }
}
