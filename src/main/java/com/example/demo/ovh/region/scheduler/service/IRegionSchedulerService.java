package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.collect.ImmutableList;

public interface IRegionSchedulerService {

    ImmutableList<String> getRegionNames();

    ProcessRegionResponse processRegions(ImmutableList<String> regionNames);
}
