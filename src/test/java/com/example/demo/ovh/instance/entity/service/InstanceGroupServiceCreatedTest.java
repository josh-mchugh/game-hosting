package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.model.InstanceGroup;
import com.example.demo.project.entity.model.Project;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceGroupServiceCreatedTest {

    @Autowired
    private IInstanceGroupService instanceGroupService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private Project project;

    @BeforeEach
    public void setup() {

        project = sampleBuilder.builder()
                .user()
                .project()
                .build()
                .getProject();
    }

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> instanceGroupService.handleCreated(null));
    }

    @Test
    public void whenEventHasIdThenExpectId() {

        UUID id = UUID.randomUUID();

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(id)
                .projectId(project.getId())
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleCreated(event);

        Assertions.assertEquals(id.toString(), instanceGroup.getId());
    }

    @Test
    public void whenEventHasNullIdThenExpectException() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> instanceGroupService.handleCreated(event));
    }

    @Test
    public void whenEventHasNullProjectIdThenExpectException() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(UUID.randomUUID())
                .projectId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> instanceGroupService.handleCreated(event));
    }

    @Test
    public void whenEventHasInvalidProjectIdThenExpectException() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(UUID.randomUUID())
                .projectId("invalid-id")
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceGroupService.handleCreated(event));
    }

    @Test
    public void whenEventHasGroupIdThenReturnGroupId() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(UUID.randomUUID())
                .projectId(project.getId())
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleCreated(event);

        Assertions.assertEquals("groupId", instanceGroup.getGroupId());
    }

    @Test
    public void whenEventHasNullGroupIdThenExpectException() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(UUID.randomUUID())
                .projectId(project.getId())
                .groupId(null)
                .name("name")
                .type("type")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> instanceGroupService.handleCreated(event));
    }

    @Test
    public void whenEventHasNameThenExpectName() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(UUID.randomUUID())
                .projectId(project.getId())
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleCreated(event);

        Assertions.assertEquals("name", instanceGroup.getName());
    }

    @Test
    public void whenEventHasTypeThenExpectType() {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(UUID.randomUUID())
                .projectId(project.getId())
                .groupId("groupId")
                .name("name")
                .type("type")
                .build();

        InstanceGroup instanceGroup = instanceGroupService.handleCreated(event);

        Assertions.assertEquals("type", instanceGroup.getType());
    }
}
