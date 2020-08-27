package com.example.demo.ovh.feign.flavor;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.feign.flavor.model.FlavorApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "flavorClient", url = "${app.ovh.base-url}", configuration = FeignOvhConfig.class)
public interface FlavorClient {

    @GetMapping("/1.0/cloud/project/{projectId}/flavor")
    List<FlavorApi> getFlavors(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/flavor/{flavorId}")
    FlavorApi getFlavorById(@PathVariable("projectId") String projectId, @PathVariable("flavorId") String flavorId);
}
