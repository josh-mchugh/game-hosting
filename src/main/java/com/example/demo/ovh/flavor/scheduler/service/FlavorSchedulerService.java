package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public interface FlavorSchedulerService {

    ImmutableList<FlavorApi> getFlavorResponses();

    ProcessedFlavorsResponse processFlavors(ImmutableList<FlavorApi> flavorResponses) throws ExecutionException, InterruptedException;
}
