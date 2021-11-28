package com.example.demo.ovh.region.scheduler.service;

import com.example.demo.ovh.region.scheduler.service.model.ProcessRegionResponse;
import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public interface RegionSchedulerService {

    ImmutableList<String> getRegionNames();

    ProcessRegionResponse processRegions(ImmutableList<String> regionNames) throws ExecutionException, InterruptedException;
}
