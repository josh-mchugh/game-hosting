package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.ovh.feign.flavor.FlavorClient;
import com.example.demo.ovh.feign.flavor.model.FlavorApi;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlavorSeedService implements ISeedService<Flavor> {

    private final OvhConfig ovhConfig;
    private final IFlavorService flavorService;
    private final FlavorClient flavorClient;

    @Override
    public boolean dataNotExists() {

        return !flavorService.existsAny();
    }

    @Override
    public ImmutableList<Flavor> initializeData() {

        return flavorClient.getFlavors(ovhConfig.getProjectId()).stream()
                .map(this::buildFlavorCreateRequest)
                .map(flavorService::handleFlavorCreate)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "Flavor";
    }

    @Override
    public Integer order() {

        return 3;
    }

    private FlavorCreateRequest buildFlavorCreateRequest(FlavorApi flavor) {

        return FlavorCreateRequest.builder()
                .flavorId(flavor.getFlavorId())
                .name(flavor.getName())
                .type(flavor.getType())
                .regionName(flavor.getRegionName())
                .hourly(flavor.getPlanCodes() != null ? flavor.getPlanCodes().getHourly() : null)
                .monthly(flavor.getPlanCodes() != null ? flavor.getPlanCodes().getMonthly() : null)
                .osType(flavor.getOsType())
                .quota(flavor.getQuota())
                .available(flavor.isAvailable())
                .vcpus(flavor.getVcpus())
                .ram(flavor.getRam())
                .disk(flavor.getDisk())
                .inboundBandwidth(flavor.getInboundBandwidth())
                .outboundBandwidth(flavor.getOutboundBandwidth())
                .build();
    }
}
