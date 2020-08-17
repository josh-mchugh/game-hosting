package com.example.demo.project.service;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.entity.ProjectMembershipEntity;
import com.example.demo.project.entity.ProjectMembershipRole;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.project.mapper.ProjectMapper;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserEntity;
import com.google.common.collect.Lists;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public Project handleProjectCreate(ProjectCreateRequest request) {

        QGameEntity qGame = QGameEntity.gameEntity;

        GameEntity gameEntity = queryFactory.select(qGame)
                .from(qGame)
                .where(qGame.type.eq(request.getGameType()))
                .fetchOne();

        ProjectEntity entity = new ProjectEntity();
        entity.setName(request.getName());
        entity.setStatus(ProjectStatus.ACTIVE);
        entity.setState(ProjectState.BUILD);
        entity.setGameEntity(gameEntity);

        QUserEntity qUser = QUserEntity.userEntity;

        UserEntity userEntity = queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(request.getUserId()))
                .fetchOne();

        ProjectMembershipEntity projectMembershipEntity = new ProjectMembershipEntity();
        projectMembershipEntity.setProjectEntity(entity);
        projectMembershipEntity.setUserEntity(userEntity);
        projectMembershipEntity.setRole(ProjectMembershipRole.OWNER);

        entity.setProjectMembershipsEntities(Lists.newArrayList(projectMembershipEntity));

        entityManager.persist(entity);

        return ProjectMapper.map(entity);
    }
}
