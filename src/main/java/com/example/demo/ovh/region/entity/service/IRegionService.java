package com.example.demo.ovh.region.entity.service;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.aggregate.event.RegionUpdatedEvent;
import com.example.demo.ovh.region.entity.model.Region;

public interface IRegionService {

    Region handleCreated(RegionCreatedEvent event);

    Region handleUpdated(RegionUpdatedEvent event);
}
