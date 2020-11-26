package com.example.demo.user.scheduler.service;

import com.google.common.collect.ImmutableList;

public interface IRecoveryTokenSchedulerService {

    ImmutableList<Object> processExpiredRecoveryTokens();
}
