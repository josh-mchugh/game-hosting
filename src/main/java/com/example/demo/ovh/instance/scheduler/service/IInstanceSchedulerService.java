package com.example.demo.ovh.instance.scheduler.service;

import java.util.List;
import java.util.UUID;

public interface IInstanceSchedulerService {

    List<UUID> handleInstanceUpdates();
}
