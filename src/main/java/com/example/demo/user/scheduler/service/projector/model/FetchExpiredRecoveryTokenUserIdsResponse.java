package com.example.demo.user.scheduler.service.projector.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchExpiredRecoveryTokenUserIdsResponse {

    Page<UUID> recoveryTokens;
}
