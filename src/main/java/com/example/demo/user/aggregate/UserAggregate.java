package com.example.demo.user.aggregate;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.user.aggregate.command.UserAuthFailCommand;
import com.example.demo.user.aggregate.command.UserAuthSuccessCommand;
import com.example.demo.user.aggregate.command.UserCreateAdminCommand;
import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.user.aggregate.command.UserPasswordChangeCommand;
import com.example.demo.user.aggregate.command.UserRecoveryTokenCreateCommand;
import com.example.demo.user.aggregate.command.UserRecoveryTokenDeleteCommand;
import com.example.demo.user.aggregate.command.UserVerifyCommand;
import com.example.demo.user.aggregate.command.UserVerifyResetCommand;
import com.example.demo.user.aggregate.event.UserAuthFailedEvent;
import com.example.demo.user.aggregate.event.UserAuthSuccessEvent;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserPasswordChangedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenDeletedEvent;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.aggregate.event.UserVerifyResetEvent;
import com.example.demo.user.aggregate.event.UserPasswordChangedEmailEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenEmailEvent;
import com.example.demo.user.aggregate.event.UserVerificationEmailEvent;
import com.example.demo.user.aggregate.event.UserVerifyResetEmailEvent;
import com.example.demo.user.aggregate.event.UserWelcomeEmailEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.VerificationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class UserAggregate {

    @AggregateIdentifier
    private UUID id;
    private String email;
    private String password;
    private UserState state;
    private UserType type;
    private Long invalidLoginAttempts;
    private LocalDateTime lastLoginDate;

    private Verification verification;
    private RecoveryToken recoveryToken;

    @Data
    public static class Verification {

        private UUID id;
        private String token;
        private VerificationStatus status;
        private LocalDateTime sentDate;
        private LocalDateTime verifiedDate;
    }

    @Data
    public static class RecoveryToken {

        private UUID id;
        private String token;
        private LocalDateTime sentDate;
        private LocalDateTime expirationDate;
    }

    @CommandHandler
    public UserAggregate(UserCreateRegularCommand command, PasswordEncoder encoder) {

        UserCreatedEvent.Verification verification = UserCreatedEvent.createVerification();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(command.getId())
                .email(command.getEmail())
                .password(encoder.encode(command.getPassword()))
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(verification)
                .build();

        AggregateLifecycle.apply(event);

        handleCreatedUserEmailEvents(command.getEmail(), verification.getToken());
    }

    @CommandHandler
    public UserAggregate(UserCreateAdminCommand command, PasswordEncoder encoder) {

        UserCreatedEvent.Verification verification = UserCreatedEvent.createVerification();

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(command.getId())
                .email(command.getEmail())
                .password(encoder.encode(command.getPassword()))
                .type(UserType.ADMIN)
                .state(UserState.ACTIVE)
                .verification(verification)
                .build();

        AggregateLifecycle.apply(event);

        handleCreatedUserEmailEvents(command.getEmail(), verification.getToken());
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {

        this.id = event.getId();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.state = event.getState();
        this.type = event.getType();
        this.invalidLoginAttempts = 0L;

        Verification verification = new Verification();
        verification.setId(event.getVerification().getId());
        verification.setToken(event.getVerification().getToken());
        verification.setStatus(VerificationStatus.UNVERIFIED);
        verification.setSentDate(event.getVerification().getSentDate());

        this.verification = verification;
    }

    @CommandHandler
    public void on(UserAuthFailCommand command) {

        AggregateLifecycle.apply(new UserAuthFailedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(UserAuthFailedEvent event) {

        this.invalidLoginAttempts += 1;
    }

    @CommandHandler
    public void on(UserAuthSuccessCommand command) {

        UserAuthSuccessEvent event = UserAuthSuccessEvent.builder()
                .id(command.getId())
                .lastLoginDate(LocalDateTime.now())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserAuthSuccessEvent event) {

        this.invalidLoginAttempts = 0L;
        this.lastLoginDate = event.getLastLoginDate();
    }

    @CommandHandler
    public void on(UserVerifyCommand command) {

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(command.getId())
                .verifiedDate(LocalDateTime.now())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserVerifiedEvent event) {

        this.verification.setStatus(VerificationStatus.VERIFIED);
        this.verification.setVerifiedDate(event.getVerifiedDate());
    }

    @CommandHandler
    public void on(UserVerifyResetCommand command) {

        String token = UUID.randomUUID().toString();

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .id(command.getId())
                .token(token)
                .sentDate(LocalDateTime.now())
                .build();

        AggregateLifecycle.apply(event);

        UserVerifyResetEmailEvent emailEvent = UserVerifyResetEmailEvent.builder()
                .email(email)
                .token(token)
                .build();

        AggregateLifecycle.apply(emailEvent);
    }

    @EventSourcingHandler
    public void on(UserVerifyResetEvent event) {

        this.verification.setToken(event.getToken());
        this.verification.setStatus(VerificationStatus.UNVERIFIED);
        this.verification.setSentDate(event.getSentDate());
        this.verification.setVerifiedDate(null);
    }

    @CommandHandler
    public void on(UserPasswordChangeCommand command, PasswordEncoder encoder) {

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .id(command.getId())
                .password(encoder.encode(command.getPassword()))
                .build();

        AggregateLifecycle.apply(event);

        UserPasswordChangedEmailEvent emailEvent = UserPasswordChangedEmailEvent.builder()
                .email(email)
                .build();

        AggregateLifecycle.apply(emailEvent);
    }

    @EventSourcingHandler
    public void on(UserPasswordChangedEvent event) {

        this.password = event.getPassword();
        this.recoveryToken = null;
    }

    @CommandHandler
    public void on(UserRecoveryTokenCreateCommand command, AppConfig appConfig) {

        Long offset = appConfig.getPassword().getRecoveryExpirationOffset();
        UserRecoveryTokenCreatedEvent.RecoveryToken recoveryToken = UserRecoveryTokenCreatedEvent.createRecoveryToken(offset);

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(command.getId())
                .recoveryToken(recoveryToken)
                .build();

        AggregateLifecycle.apply(event);

        UserRecoveryTokenEmailEvent emailEvent = UserRecoveryTokenEmailEvent.builder()
                .email(email)
                .token(recoveryToken.getToken())
                .build();

        AggregateLifecycle.apply(emailEvent);
    }

    @EventSourcingHandler
    public void on(UserRecoveryTokenCreatedEvent event) {

        RecoveryToken recoveryToken = new RecoveryToken();
        recoveryToken.setId(event.getRecoveryToken().getId());
        recoveryToken.setToken(event.getRecoveryToken().getToken());
        recoveryToken.setSentDate(event.getRecoveryToken().getSentDate());
        recoveryToken.setExpirationDate(event.getRecoveryToken().getExpirationDate());

        this.recoveryToken = recoveryToken;
    }

    @CommandHandler
    public void on(UserRecoveryTokenDeleteCommand command) {

        AggregateLifecycle.apply(new UserRecoveryTokenDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(UserRecoveryTokenDeletedEvent event) {

        this.recoveryToken = null;
    }

    private void handleCreatedUserEmailEvents(String email, String token) {

        AggregateLifecycle.apply(new UserWelcomeEmailEvent(email));

        UserVerificationEmailEvent verificationEmailEvent = UserVerificationEmailEvent.builder()
                .email(email)
                .token(token)
                .build();

        AggregateLifecycle.apply(verificationEmailEvent);
    }
}
