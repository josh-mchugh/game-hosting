package com.example.demo.ovh.region.feign;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.region.feign.model.RegionApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "regionClient", url = "${ovh.base-url}", configuration = FeignOvhConfig.class)
public interface IRegionClient {

    @GetMapping("/1.0/cloud/project/{projectId}/region")
    List<String> getRegions(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/region/{regionId}")
    RegionApi getRegion(@PathVariable("projectId") String projectId, @PathVariable("regionId") String regionId);
}
