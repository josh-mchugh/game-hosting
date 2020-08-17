package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.ovh.feign.model.OvhFlavorApiResponse;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.google.common.collect.ImmutableList;

public interface IFlavorSchedulerService {

    ImmutableList<OvhFlavorApiResponse> getFlavorResponses();

    ProcessedFlavorsResponse processFlavors(ImmutableList<OvhFlavorApiResponse> flavorResponses);
}
