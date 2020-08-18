package com.example.demo.ovh.instance.service;

import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .user()
                .game()
                .project()
                .build();
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
                .projectId(data.getProject().getId())
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
                .projectId(data.getProject().getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertNotNull(instanceGroup.getId());
    }

    @Test
    public void whenCreateRequestHasGroupIdThenReturnGroupId() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(data.getProject().getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertEquals("instance-group-id", instanceGroup.getGroupId());
    }

    @Test
    public void whenCreateRequestHasNullGroupIdThenThrowException() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .projectId(data.getProject().getId())
                .build();

        Exception exception = Assertions.assertThrows(PersistenceException.class, () -> instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(data.getProject().getId())
                .name("name")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertEquals("name", instanceGroup.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenReturnName() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(data.getProject().getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertNull(instanceGroup.getName());
    }

    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(data.getProject().getId())
                .type("type")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertEquals("type", instanceGroup.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnNull() {

        InstanceGroupCreateRequest instanceGroupCreateRequest = InstanceGroupCreateRequest.builder()
                .instanceGroupId("instance-group-id")
                .projectId(data.getProject().getId())
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleInstanceGroupCreate(instanceGroupCreateRequest);

        Assertions.assertNull(instanceGroup.getType());
    }
}
