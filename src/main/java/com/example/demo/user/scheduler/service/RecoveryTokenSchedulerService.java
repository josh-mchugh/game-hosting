package com.example.demo.user.scheduler.service;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public interface RecoveryTokenSchedulerService {

    ImmutableList<Object> processExpiredRecoveryTokens() throws ExecutionException, InterruptedException;
}
