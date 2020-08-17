package com.example.demo.ovh.flavor.service;

import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.flavor.service.model.FlavorUpdateRequest;

public interface IFlavorService {

    boolean existsAny();

    boolean existsByFlavorId(String flavorId);

    Flavor handleFlavorCreate(FlavorCreateRequest request);

    Flavor handleFlavorUpdate(FlavorUpdateRequest request);
}
