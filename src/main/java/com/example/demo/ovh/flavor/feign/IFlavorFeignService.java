package com.example.demo.ovh.flavor.feign;

import com.example.demo.ovh.flavor.feign.model.FlavorApi;

import java.util.List;

public interface IFlavorFeignService {

    List<FlavorApi> getFlavors();
}
