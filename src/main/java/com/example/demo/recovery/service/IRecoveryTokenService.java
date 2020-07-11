package com.example.demo.recovery.service;

import com.example.demo.recovery.model.RecoveryToken;

import java.util.List;

public interface IRecoveryTokenService {

    RecoveryToken handleCreateRecoveryToken(String email);

    boolean existsRecoveryToken(String id);

    boolean existsExpiredRecoveryTokens();

    List<RecoveryToken> getExpiredRecoveryTokens();

    void handleDeleteRecoveryToken(String id);
}
