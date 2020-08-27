package com.example.demo.ovh.feign.region;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.feign.region.model.AvailableRegionApi;
import com.example.demo.ovh.feign.region.model.RegionApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "regionClient", url = "${app.ovh.base-url}", configuration = FeignOvhConfig.class)
public interface RegionClient {

    @GetMapping("/1.0/cloud/project/{projectId}/region")
    List<String> getRegions(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/region/{regionId}")
    RegionApi getRegion(@PathVariable("projectId") String projectId, @PathVariable("regionId") String regionId);

    @GetMapping("1.0/cloud/project/{projectId}/regionAvailable")
    List<AvailableRegionApi> getAvailableRegions(@PathVariable("projectId") String projectId);
}
