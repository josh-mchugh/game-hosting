package com.example.demo.ovh.region.feign;

import com.example.demo.ovh.region.feign.model.RegionApi;

import java.util.List;

public interface IRegionFeignService {

    List<String> getRegions();

    RegionApi getRegion(String regionName);
}
