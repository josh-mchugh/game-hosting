package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectMembershipRole;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .status(ProjectStatus.ACTIVE)
                .build();

        Assertions.assertEquals(ProjectStatus.ACTIVE, event.getStatus());
    }

    @Test
    public void whenEventHasStateThenReturnState() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .state(ProjectState.ACTIVE)
                .build();

        Assertions.assertEquals(ProjectState.ACTIVE, event.getState());
    }

    @Test
    public void whenEventHasGameIdThenReturnGameId() {

        UUID gameId = UUID.randomUUID();

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .gameId(gameId)
                .build();

        Assertions.assertEquals(gameId, event.getGameId());
    }

    @Test
    public void whenEventHasOwnerThenReturnNotNull() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .member(ProjectCreatedEvent.createOwner(UUID.randomUUID()))
                .build();

        Assertions.assertNotNull(event.getMember());
    }

    @Test
    public void whenEventHasMemberThenReturnNotNull() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .member(ProjectCreatedEvent.createMember(UUID.randomUUID(), ProjectMembershipRole.OWNER))
                .build();

        Assertions.assertNotNull(event.getMember());
    }

    @Test
    public void whenMemberHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.Member.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, member.getId());
    }

    @Test
    public void whenMemberHasUserIdThenReturnUserId() {

        UUID userId = UUID.randomUUID();

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.Member.builder()
                .userId(userId)
                .build();

        Assertions.assertEquals(userId, member.getUserId());
    }

    @Test
    public void whenMemberToString() {

        ProjectCreatedEvent.Member member = member();

        String expected = "ProjectCreatedEvent.Member(id=26fe10e0-dce0-4293-943e-67541bd1159d, userId=682fdeb3-325b-403d-aca2-0a132f27ad56, role=OWNER)";

        Assertions.assertEquals(expected, member.toString());
    }

    @Test
    public void whenMemberHashCode() {

        ProjectCreatedEvent.Member member =ProjectCreatedEvent.Member.builder()
                .id(UUID.fromString("26fe10e0-dce0-4293-943e-67541bd1159d"))
                .userId(UUID.fromString("682fdeb3-325b-403d-aca2-0a132f27ad56"))
                .build();

        Assertions.assertEquals(-161385119, member.hashCode());
    }

    @Test
    public void whenMemberEquals() {

        ProjectCreatedEvent.Member member1 = member();
        ProjectCreatedEvent.Member member2 = member();

        Assertions.assertEquals(member1, member2);
    }

    @Test
    public void whenMemberNotEquals() {

        ProjectCreatedEvent.Member member = member();

        Assertions.assertNotEquals(member, ProjectCreatedEvent.Member.builder().build());
    }

    @Test
    public void whenCreateOwnerThenExpectRandomUUID() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createOwner(UUID.randomUUID());

        Assertions.assertNotNull(member.getId());
    }

    @Test
    public void whenCreateMemberThenExpectRandomUUID() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createMember(UUID.randomUUID(), ProjectMembershipRole.OWNER);

        Assertions.assertNotNull(member.getId());
    }

    @Test
    public void whenCreateOwnerThenExpectUserId() {

        UUID userId = UUID.randomUUID();

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createOwner(userId);

        Assertions.assertEquals(userId, member.getUserId());
    }

    @Test
    public void whenCreateMemberThenExpectUserId() {

        UUID userId = UUID.randomUUID();

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createMember(userId, ProjectMembershipRole.OWNER);

        Assertions.assertEquals(userId, member.getUserId());
    }

    @Test
    public void whenCreateOwnerThenExpectRole() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createOwner(UUID.randomUUID());

        Assertions.assertEquals(ProjectMembershipRole.OWNER, member.getRole());
    }

    @Test
    public void whenCreateMemberWithRoleThenExpectRole() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createMember(null, ProjectMembershipRole.OWNER);

        Assertions.assertEquals(ProjectMembershipRole.OWNER, member.getRole());
    }

    @Test
    public void whenEventToString() {

        ProjectCreatedEvent event = event();

        String expected = "ProjectCreatedEvent(id=28245910-9ea6-4d67-8849-65d982e4af78, name=name, status=CONFIG, state=CONFIG_REGION, gameId=a5bc7b4e-f252-49e6-9311-da3311f1e466, member=ProjectCreatedEvent.Member(id=26fe10e0-dce0-4293-943e-67541bd1159d, userId=682fdeb3-325b-403d-aca2-0a132f27ad56, role=OWNER))";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.Member.builder()
                .id(UUID.fromString("26fe10e0-dce0-4293-943e-67541bd1159d"))
                .userId(UUID.fromString("682fdeb3-325b-403d-aca2-0a132f27ad56"))
                .build();

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.fromString("28245910-9ea6-4d67-8849-65d982e4af78"))
                .gameId(UUID.fromString("a5bc7b4e-f252-49e6-9311-da3311f1e466"))
                .name("name")
                .member(member)
                .build();

        Assertions.assertEquals(702509754, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        ProjectCreatedEvent event1 = event();
        ProjectCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        ProjectCreatedEvent event = event();

        Assertions.assertNotEquals(event, ProjectCreatedEvent.builder().build());
    }

    private ProjectCreatedEvent event() {

        return ProjectCreatedEvent.builder()
                .id(UUID.fromString("28245910-9ea6-4d67-8849-65d982e4af78"))
                .gameId(UUID.fromString("a5bc7b4e-f252-49e6-9311-da3311f1e466"))
                .status(ProjectStatus.CONFIG)
                .state(ProjectState.CONFIG_REGION)
                .name("name")
                .member(member())
                .build();
    }

    private ProjectCreatedEvent.Member member() {

        return ProjectCreatedEvent.Member.builder()
                .id(UUID.fromString("26fe10e0-dce0-4293-943e-67541bd1159d"))
                .userId(UUID.fromString("682fdeb3-325b-403d-aca2-0a132f27ad56"))
                .role(ProjectMembershipRole.OWNER)
                .build();
    }
}
