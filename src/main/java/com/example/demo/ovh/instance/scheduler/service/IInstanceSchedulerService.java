package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.UUID;

public interface IInstanceSchedulerService {

    ImmutableList<InstanceApi> getInstanceApis();

    List<UUID> handleInstanceUpdates(ImmutableList<InstanceApi> instanceApis);
}
