package com.example.demo.user.entity;

import com.example.demo.project.entity.ProjectMembershipEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserEntity entity = new UserEntity();
        entity.setId(id);

        Assertions.assertEquals(id, entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        UserEntity entity = new UserEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        UserEntity entity = new UserEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        UserEntity entity = new UserEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        UserEntity entity = new UserEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        UserEntity entity = new UserEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasEmailThenReturnEmail() {

        UserEntity entity = new UserEntity();
        entity.setEmail("test@test");

        Assertions.assertEquals("test@test", entity.getEmail());
    }

    @Test
    public void whenEntityHasPasswordThenReturnPassword() {

        UserEntity entity = new UserEntity();
        entity.setPassword("password");

        Assertions.assertEquals("password", entity.getPassword());
    }

    @Test
    public void whenEntityHasStateThenReturnState() {

        UserEntity entity = new UserEntity();
        entity.setState(UserState.ACTIVE);

        Assertions.assertEquals(UserState.ACTIVE, entity.getState());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        UserEntity entity = new UserEntity();
        entity.setType(UserType.REGULAR);

        Assertions.assertEquals(UserType.REGULAR, entity.getType());
    }

    @Test
    public void whenEntityHasInvalidLoginAttemptsThenReturnInvalidLoginAttempts() {

        UserEntity entity = new UserEntity();
        entity.setInvalidLoginAttempts(1L);

        Assertions.assertEquals(1L, entity.getInvalidLoginAttempts());
    }

    @Test
    public void whenEntityHasLastLoginDateThenReturnLastLoginDate() {

        LocalDateTime lastLoginDate = LocalDateTime.now();

        UserEntity entity = new UserEntity();
        entity.setLastLoginDate(lastLoginDate);

        Assertions.assertEquals(lastLoginDate, entity.getLastLoginDate());
    }

    @Test
    public void whenEntityHasRecoveryTokenThenReturnRecoveryToken() {

        RecoveryTokenEntity recoveryTokenEntity = new RecoveryTokenEntity();

        UserEntity entity = new UserEntity();
        entity.setRecoveryTokenEntity(recoveryTokenEntity);

        Assertions.assertEquals(recoveryTokenEntity, entity.getRecoveryTokenEntity());
    }

    @Test
    public void whenEntityHasVerificationTokenThenReturnVerificationToken() {

        VerificationEntity verificationEntity = new VerificationEntity();

        UserEntity entity = new UserEntity();
        entity.setVerificationEntity(verificationEntity);

        Assertions.assertEquals(verificationEntity, entity.getVerificationEntity());
    }

    @Test
    public void whenEntityHasProjectMembershipsThenReturnProjectMemberships() {

        List<ProjectMembershipEntity> projectMemberships = new ArrayList<>();
        projectMemberships.add(new ProjectMembershipEntity());

        UserEntity entity = new UserEntity();
        entity.setProjectMemberEntities(projectMemberships);

        Assertions.assertEquals(projectMemberships, entity.getProjectMemberEntities());
    }

}
