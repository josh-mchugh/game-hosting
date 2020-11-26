package com.example.demo.user.aggregate.event;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasEmailThenReturnEmail() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .email("test@test")
                .build();

        Assertions.assertEquals("test@test", event.getEmail());
    }

    @Test
    public void whenEventHasPasswordThenReturnPassword() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .password("password")
                .build();


        Assertions.assertEquals("password", event.getPassword());
    }

    @Test
    public void whenEventHasStateThenReturnState() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .state(UserState.ACTIVE)
                .build();

        Assertions.assertEquals(UserState.ACTIVE, event.getState());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .type(UserType.REGULAR)
                .build();

        Assertions.assertEquals(UserType.REGULAR, event.getType());
    }

    @Test
    public void whenEventHasVerificationThenReturnNonNull() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .verification(UserCreatedEvent.createVerification())
                .build();

        Assertions.assertNotNull(event.getVerification());
    }

    @Test
    public void whenVerificationHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, verification.getId());
    }

    @Test
    public void whenVerificationHasTokenThenReturnToken() {

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", verification.getToken());
    }

    @Test
    public void whenVerificationHasSendDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        UserCreatedEvent.Verification verification = UserCreatedEvent.Verification.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, verification.getSentDate());
    }

    @Test
    public void whenVerificationToString() {

        UserCreatedEvent.Verification verification = verification();

        String expected = "UserCreatedEvent.Verification(id=3fd57594-0457-4ad8-a418-28a02780bbed, token=token, sentDate=2020-11-21T12:45)";

        Assertions.assertEquals(expected, verification.toString());
    }

    @Test
    public void whenVerificationHashCode() {

        UserCreatedEvent.Verification verification = verification();

        Assertions.assertEquals(-730084438, verification.hashCode());
    }

    @Test
    public void whenVerificationEquals() {

        UserCreatedEvent.Verification verification1 = verification();
        UserCreatedEvent.Verification verification2 = verification();

        Assertions.assertEquals(verification1, verification2);
    }

    @Test
    public void whenVerificationNotEquals() {

        UserCreatedEvent.Verification verification = verification();

        Assertions.assertNotEquals(verification, UserCreatedEvent.Verification.builder().build());
    }

    @Test
    public void whenEventToString() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.fromString("24ee87f0-d57e-41b5-babc-3033e988f638"))
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .verification(verification())
                .build();

        String expected = "UserCreatedEvent(id=24ee87f0-d57e-41b5-babc-3033e988f638, email=test@test, password=password, state=ACTIVE, type=REGULAR, verification=UserCreatedEvent.Verification(id=3fd57594-0457-4ad8-a418-28a02780bbed, token=token, sentDate=2020-11-21T12:45))";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserCreatedEvent event = event();

        Assertions.assertEquals(-495424198, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserCreatedEvent event1 = event();
        UserCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserCreatedEvent event = event();

        Assertions.assertNotEquals(event, UserCreatedEvent.builder().build());
    }

    private UserCreatedEvent event() {


        return UserCreatedEvent.builder()
                .id(UUID.fromString("24ee87f0-d57e-41b5-babc-3033e988f638"))
                .email("test@test")
                .password("password")
                .verification(verification())
                .build();
    }

    private UserCreatedEvent.Verification verification() {

        return UserCreatedEvent.Verification.builder()
                .id(UUID.fromString("3fd57594-0457-4ad8-a418-28a02780bbed"))
                .token("token")
                .sentDate(LocalDateTime.of(2020, 11, 21, 12, 45))
                .build();
    }
}
