package com.example.demo.ovh.flavor.entity.service;

import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.aggregate.event.FlavorUpdatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;

public interface IFlavorService {

    Flavor handleCreated(FlavorCreatedEvent event);

    Flavor handleUpdated(FlavorUpdatedEvent event);
}
