package com.example.demo.user.entity.model;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        User user = User.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, user.getId());
    }

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        User user = User.builder()
                .email("test@test")
                .build();

        Assertions.assertEquals("test@test", user.getEmail());
    }

    @Test
    public void whenModelHasPasswordThenReturnPassword() {

        User user = User.builder()
                .password("password")
                .build();

        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        User user = User.builder()
                .state(UserState.ACTIVE)
                .build();

        Assertions.assertEquals(UserState.ACTIVE, user.getState());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        User user = User.builder()
                .type(UserType.REGULAR)
                .build();

        Assertions.assertEquals(UserType.REGULAR, user.getType());
    }

    @Test
    public void whenModelHasInvalidLoginAttemptsThenReturnInvalidLoginAttempts() {

        User user = User.builder()
                .invalidLoginAttempts(1L)
                .build();

        Assertions.assertEquals(1L, user.getInvalidLoginAttempts());
    }

    @Test
    public void whenModelHasRecoveryTokenThenReturnRecoveryToken() {

        RecoveryToken recoveryToken = RecoveryToken.builder().build();

        User user = User.builder()
                .recoveryToken(recoveryToken)
                .build();

        Assertions.assertEquals(recoveryToken, user.getRecoveryToken());
    }

    @Test
    public void whenModelHasVerificationThenReturnVerification() {

        Verification verification = Verification.builder().build();

        User user = User.builder()
                .verification(verification)
                .build();

        Assertions.assertEquals(verification, user.getVerification());
    }

    @Test
    public void whenModelToString() {

        User user = user();

        String expected = "User(id=bb6e0650-e491-4155-8a3a-0a4dbe007bf9, email=test@test, password=password, state=ACTIVE, type=REGULAR, invalidLoginAttempts=1, lastLoginDate=2020-11-22T11:40, recoveryToken=RecoveryToken(id=null, token=null, sentDate=null, expirationDate=null), verification=Verification(id=null, status=null, token=null, sentDate=null, verificationDate=null))";

        Assertions.assertEquals(expected, user.toString());
    }

    @Test
    public void whenModelHashCode() {

        User user = User.builder()
                .id(UUID.fromString("bb6e0650-e491-4155-8a3a-0a4dbe007bf9"))
                .email("test@test")
                .password("password")
                .invalidLoginAttempts(1L)
                .lastLoginDate(LocalDateTime.of(2020, 11, 22, 11, 40))
                .recoveryToken(RecoveryToken.builder().build())
                .verification(Verification.builder().build())
                .build();

        Assertions.assertEquals(337694457, user.hashCode());
    }

    @Test
    public void whenModelEquals() {

        User user1 = user();
        User user2 = user();

        Assertions.assertEquals(user1, user2);
    }

    @Test
    public void whenModelNotEquals() {

        User user = user();

        Assertions.assertNotEquals(user, User.builder().build());
    }

    private User user() {

        return User.builder()
                .id(UUID.fromString("bb6e0650-e491-4155-8a3a-0a4dbe007bf9"))
                .email("test@test")
                .password("password")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .invalidLoginAttempts(1L)
                .lastLoginDate(LocalDateTime.of(2020, 11, 22, 11, 40))
                .recoveryToken(RecoveryToken.builder().build())
                .verification(Verification.builder().build())
                .build();
    }
}
