package com.example.demo.ovh.flavor.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FlavorFeignService implements IFlavorFeignService {

    private final OvhConfig ovhConfig;
    private final IFlavorClient flavorClient;

    @Override
    public List<FlavorApi> getFlavors() {

        return flavorClient.getFlavors(ovhConfig.getProjectId());
    }
}
