package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface InstanceSchedulerService {

    ImmutableList<InstanceApi> getInstanceApis();

    List<UUID> handleInstanceUpdates(ImmutableList<InstanceApi> instanceApis) throws ExecutionException, InterruptedException;
}
