package com.example.demo.ovh.flavor.scheduler.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.ovh.feign.OvhClient;
import com.example.demo.ovh.feign.model.OvhFlavorApiResponse;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.scheduler.service.model.ProcessedFlavorsResponse;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.flavor.service.model.FlavorUpdateRequest;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlavorSchedulerService implements IFlavorSchedulerService {

    private final AppConfig appConfig;
    private final OvhClient ovhClient;
    private final IFlavorService flavorService;

    @Override
    public ImmutableList<OvhFlavorApiResponse> getFlavorResponses() {

        return ImmutableList.copyOf(ovhClient.getFlavors(appConfig.getOvh().getProjectId()));
    }

    @Override
    public ProcessedFlavorsResponse processFlavors(ImmutableList<OvhFlavorApiResponse> flavorResponses) {

        ProcessedFlavorsResponse.Builder builder = ProcessedFlavorsResponse.builder();

        for(OvhFlavorApiResponse flavorResponse : flavorResponses) {

            if (flavorService.existsByFlavorId(flavorResponse.getFlavorId())) {

                builder.updatedFlavor(handleFlavorUpdate(flavorResponse));

            } else {

                builder.createdFlavor(handleFlavorCreate(flavorResponse));
            }

        }

        return builder.build();
    }

    private Flavor handleFlavorUpdate(OvhFlavorApiResponse flavorResponse) {

        String hourly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getHourly() : null;
        String monthly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getMonthly() : null;

        FlavorUpdateRequest request = FlavorUpdateRequest.builder()
                .flavorId(flavorResponse.getFlavorId())
                .regionName(flavorResponse.getRegionName())
                .name(flavorResponse.getName())
                .type(flavorResponse.getType())
                .available(flavorResponse.isAvailable())
                .hourly(hourly)
                .monthly(monthly)
                .osType(flavorResponse.getOsType())
                .quota(flavorResponse.getQuota())
                .vcpus(flavorResponse.getVcpus())
                .ram(flavorResponse.getRam())
                .disk(flavorResponse.getDisk())
                .inboundBandwidth(flavorResponse.getInboundBandwidth())
                .outboundBandwidth(flavorResponse.getOutboundBandwidth())
                .build();

        return flavorService.handleFlavorUpdate(request);
    }

    private Flavor handleFlavorCreate(OvhFlavorApiResponse flavorResponse) {

        String hourly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getHourly() : null;
        String monthly = flavorResponse.getPlanCodes() != null ? flavorResponse.getPlanCodes().getMonthly() : null;

        FlavorCreateRequest request = FlavorCreateRequest.builder()
                .flavorId(flavorResponse.getFlavorId())
                .regionName(flavorResponse.getRegionName())
                .name(flavorResponse.getName())
                .type(flavorResponse.getType())
                .available(flavorResponse.isAvailable())
                .hourly(hourly)
                .monthly(monthly)
                .osType(flavorResponse.getOsType())
                .quota(flavorResponse.getQuota())
                .vcpus(flavorResponse.getVcpus())
                .ram(flavorResponse.getRam())
                .disk(flavorResponse.getDisk())
                .inboundBandwidth(flavorResponse.getInboundBandwidth())
                .outboundBandwidth(flavorResponse.getOutboundBandwidth())
                .build();

        return flavorService.handleFlavorCreate(request);
    }
}
