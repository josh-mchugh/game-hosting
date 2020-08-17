package com.example.demo.ovh.region.service;

import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;
import com.example.demo.ovh.region.service.model.RegionUpdateRequest;

public interface IRegionService {

    boolean existsByName(String name);

    boolean existsAny();

    Region handleRegionCreate(RegionCreateRequest request);

    Region handleRegionUpdate(RegionUpdateRequest request);
}
