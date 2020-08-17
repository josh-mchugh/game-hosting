package com.example.demo.ovh.instance.service;

import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.TestGameUtil;
import com.example.demo.sample.TestProjectUtil;
import com.example.demo.sample.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceGroupServiceHandleInstanceGroupCreateTest {

    @Autowired
    private IInstanceGroupService instanceGroupService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGameService gameService;

    private Project project;

    @BeforeEach
    public void setup() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("test@test");
        User user = userService.handleCreateUser(userCreateRequest);

        GameCreateRequest gameCreateRequest = TestGameUtil.builder().build();
        Game game = gameService.handleGameCreateRequest(gameCreateRequest);

        ProjectCreateRequest projectCreateRequest = TestProjectUtil.builder()
                .name("project")
                .userId(user)
                .gameType(game)
                .build();
        project = projectService.handleProjectCreate(projectCreateRequest);
    }

    @Test
    public void whenCreateRequestIsNullThenThrowException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> instanceGroupService.handleInstanceGroupCreate(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestValidThenReturnInstanceGroup() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(project.getId())
                .name("name")
                .type("type")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertNotNull(instanceGroup);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNonNullId() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(project.getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertNotNull(instanceGroup.getId());
    }

    @Test
    public void whenCreateRequestHasGroupIdThenReturnGroupId() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(project.getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertEquals("instance-group-id", instanceGroup.getGroupId());
    }

    @Test
    public void whenCreateRequestHasNullGroupIdThenThrowException() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .projectId(project.getId())
                .build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(project.getId())
                .name("name")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertEquals("name", instanceGroup.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenReturnName() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(project.getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertNull(instanceGroup.getName());
    }

    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(project.getId())
                .type("type")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertEquals("type", instanceGroup.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnNull() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(project.getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertNull(instanceGroup.getType());
    }
}
