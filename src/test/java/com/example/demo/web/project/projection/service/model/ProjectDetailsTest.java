package com.example.demo.web.project.projection.service.model;

import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectDetailsTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        ProjectDetails model = ProjectDetails.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        ProjectDetails model = ProjectDetails.builder()
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelHasInstanceIdThenReturnInstanceId() {

        ProjectDetails model = ProjectDetails.builder()
                .instanceId("instanceId")
                .build();

        Assertions.assertEquals("instanceId", model.getInstanceId());
    }

    @Test
    public void whenModelHasInstanceStatusThenReturnInstanceStatus() {

        ProjectDetails model = ProjectDetails.builder()
                .instanceStatus(InstanceStatus.ACTIVE)
                .build();

        Assertions.assertEquals(InstanceStatus.ACTIVE, model.getInstanceStatus());
    }

    @Test
    public void whenModelHasIp4AddressThenReturnIp4Address() {

        ProjectDetails model = ProjectDetails.builder()
                .ip4Address("ip4Address")
                .build();

        Assertions.assertEquals("ip4Address", model.getIp4Address());
    }

    @Test
    public void whenModelToString() {

        ProjectDetails model = model();

        String expected = "ProjectDetails(name=name, gameType=MINECRAFT_JAVA, instanceId=instanceId, instanceStatus=ACTIVE, ip4Address=ip4Address)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectDetails model = ProjectDetails.builder()
                .name("name")
                .instanceId("instanceId")
                .ip4Address("ip4Address")
                .build();

        Assertions.assertEquals(-1100418073, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectDetails model1 = model();
        ProjectDetails model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectDetails model = model();

        Assertions.assertNotEquals(model, ProjectDetails.builder().build());
    }

    private ProjectDetails model() {

        return ProjectDetails.builder()
                .name("name")
                .gameType(GameType.MINECRAFT_JAVA)
                .instanceId("instanceId")
                .instanceStatus(InstanceStatus.ACTIVE)
                .ip4Address("ip4Address")
                .build();
    }
}
