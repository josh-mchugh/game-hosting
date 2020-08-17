package com.example.demo.user.scheduler.service;

import com.example.demo.user.model.User;
import com.google.common.collect.ImmutableList;

public interface IRecoveryTokenSchedulerService {

    ImmutableList<User> processExpiredRecoveryTokens();
}
