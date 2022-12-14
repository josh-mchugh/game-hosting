package com.example.demo.user.scheduler.service.projector;

import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsQuery;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsResponse;

public interface RecoveryTokenProjectorService {

    FetchExpiredRecoveryTokenUserIdsResponse fetchExpiredTokenUserIds(FetchExpiredRecoveryTokenUserIdsQuery request);
}
