package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserAuthFailedEvent;
import com.example.demo.user.aggregate.event.UserAuthSuccessEvent;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserPasswordChangedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenDeletedEvent;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.aggregate.event.UserVerifyResetEvent;
import com.example.demo.user.entity.model.User;

public interface IUserService {

    User handleCreated(UserCreatedEvent event);

    User handleAuthFailed(UserAuthFailedEvent event);

    User handleAuthSuccess(UserAuthSuccessEvent event);

    User handleVerified(UserVerifiedEvent event);

    User handleVerifyReset(UserVerifyResetEvent event);

    User handlePasswordChanged(UserPasswordChangedEvent event);

    User handleRecoveryTokenCreated(UserRecoveryTokenCreatedEvent event);

    User handleRecoveryTokenDelete(UserRecoveryTokenDeletedEvent event);
}
