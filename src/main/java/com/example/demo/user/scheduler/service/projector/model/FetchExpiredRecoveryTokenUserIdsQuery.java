package com.example.demo.user.scheduler.service.projector.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
@AllArgsConstructor
public class FetchExpiredRecoveryTokenUserIdsQuery {

    Pageable pageable;
}
