package com.example.demo.user.scheduler.service;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public interface IRecoveryTokenSchedulerService {

    ImmutableList<Object> processExpiredRecoveryTokens() throws ExecutionException, InterruptedException;
}
