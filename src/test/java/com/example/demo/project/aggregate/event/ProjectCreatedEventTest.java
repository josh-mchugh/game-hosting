package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectMembershipRole;
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
    public void whenEventHasGameIdThenReturnGameId() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .gameId("gameId")
                .build();

        Assertions.assertEquals("gameId", event.getGameId());
    }

    @Test
    public void whenEventHasMemberThenReturnNotNull() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .member(ProjectCreatedEvent.createMember("userId"))
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

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.Member.builder()
                .userId("userId")
                .build();

        Assertions.assertEquals("userId", member.getUserId());
    }

    @Test
    public void whenMemberToString() {

        ProjectCreatedEvent.Member member = member();

        String expected = "ProjectCreatedEvent.Member(id=26fe10e0-dce0-4293-943e-67541bd1159d, userId=userId)";

        Assertions.assertEquals(expected, member.toString());
    }

    @Test
    public void whenMemberHashCode() {

        ProjectCreatedEvent.Member member = member();

        Assertions.assertEquals(-54671683, member.hashCode());
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
    public void whenCreatedMemberThenExpectRandomUUID() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createMember("userId");

        Assertions.assertNotNull(member.getId());
    }

    @Test
    public void whenCreateMemberThenExpectUserId() {

        ProjectCreatedEvent.Member member = ProjectCreatedEvent.createMember("userId");

        Assertions.assertEquals("userId", member.getUserId());
    }

    @Test
    public void whenEventToString() {

        ProjectCreatedEvent event = event();

        String expected = "ProjectCreatedEvent(id=28245910-9ea6-4d67-8849-65d982e4af78, name=name, gameId=gameId, member=ProjectCreatedEvent.Member(id=26fe10e0-dce0-4293-943e-67541bd1159d, userId=userId))";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        ProjectCreatedEvent event = event();

        Assertions.assertEquals(-1159971294, event.hashCode());
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
                .name("name")
                .gameId("gameId")
                .member(member())
                .build();
    }

    private ProjectCreatedEvent.Member member() {

        return ProjectCreatedEvent.Member.builder()
                .id(UUID.fromString("26fe10e0-dce0-4293-943e-67541bd1159d"))
                .userId("userId")
                .build();
    }
}
