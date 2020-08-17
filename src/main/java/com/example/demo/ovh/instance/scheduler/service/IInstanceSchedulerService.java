package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.ovh.instance.model.Instance;
import com.google.common.collect.ImmutableList;

public interface IInstanceSchedulerService {

    ImmutableList<Instance> handleInstanceUpdates();
}
