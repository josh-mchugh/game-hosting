package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.ovh.feign.flavor.model.FlavorApi;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.google.common.collect.ImmutableList;

public interface IFlavorSchedulerService {

    ImmutableList<FlavorApi> getFlavorResponses();

    ProcessedFlavorsResponse processFlavors(ImmutableList<FlavorApi> flavorResponses);
}
