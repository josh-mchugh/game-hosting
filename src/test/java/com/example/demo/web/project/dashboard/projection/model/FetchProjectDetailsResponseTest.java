package com.example.demo.web.project.dashboard.projection.model;

import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectDetailsResponseTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .status(ProjectStatus.ACTIVE)
                .build();

        Assertions.assertEquals(ProjectStatus.ACTIVE, model.getStatus());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .state(ProjectState.ACTIVE)
                .build();

        Assertions.assertEquals(ProjectState.ACTIVE, model.getState());
    }

    @Test
    public void whenModelHasInstanceIdThenReturnInstanceId() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .instanceId("instanceId")
                .build();

        Assertions.assertEquals("instanceId", model.getInstanceId());
    }

    @Test
    public void whenModelHasInstanceStatusThenReturnInstanceStatus() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .instanceStatus(InstanceStatus.ACTIVE)
                .build();

        Assertions.assertEquals(InstanceStatus.ACTIVE, model.getInstanceStatus());
    }

    @Test
    public void whenModelHasIp4AddressThenReturnIp4Address() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .ip4Address("ip4Address")
                .build();

        Assertions.assertEquals("ip4Address", model.getIp4Address());
    }

    @Test
    public void whenModelToString() {

        FetchProjectDetailsResponse model = model();

        String expected = "FetchProjectDetailsResponse(name=name, gameType=MINECRAFT_JAVA, status=ACTIVE, state=ACTIVE, instanceId=instanceId, instanceStatus=ACTIVE, ip4Address=ip4Address)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectDetailsResponse model = FetchProjectDetailsResponse.builder()
                .name("name")
                .instanceId("instanceId")
                .ip4Address("ip4Address")
                .build();

        Assertions.assertEquals(1353227595, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectDetailsResponse model1 = model();
        FetchProjectDetailsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectDetailsResponse model = model();

        Assertions.assertNotEquals(model, FetchProjectDetailsResponse.builder().build());
    }

    private FetchProjectDetailsResponse model() {

        return FetchProjectDetailsResponse.builder()
                .name("name")
                .gameType(GameType.MINECRAFT_JAVA)
                .status(ProjectStatus.ACTIVE)
                .state(ProjectState.ACTIVE)
                .instanceId("instanceId")
                .instanceStatus(InstanceStatus.ACTIVE)
                .ip4Address("ip4Address")
                .build();
    }
}
