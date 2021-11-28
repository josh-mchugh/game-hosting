package com.example.demo.web.admin.ovh.service;

import com.example.demo.ovh.instance.feign.InstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.web.admin.ovh.model.InstanceGroupStatistic;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminOvhUtilsServiceImpl implements AdminOvhUtilsService {

    private final InstanceGroupFeignService instanceGroupFeignService;

    @Override
    public InstanceGroupStatistic getInstanceGroupStatistics() {

        List<InstanceGroupApi> instanceGroupApis = getInactiveInstanceGroups();

        return new InstanceGroupStatistic(CollectionUtils.size(instanceGroupApis));
    }

    @Override
    public InstanceGroupStatistic handleInstanceGroupDelete() {

        getInactiveInstanceGroups().forEach(api -> instanceGroupFeignService.deleteInstanceGroupById(api.getId()));

        List<InstanceGroupApi> instanceGroupApis = getInactiveInstanceGroups();

        return new InstanceGroupStatistic(CollectionUtils.size(instanceGroupApis));
    }

    private List<InstanceGroupApi> getInactiveInstanceGroups() {

        return instanceGroupFeignService.getInstanceGroups().stream()
                .filter(api -> CollectionUtils.isEmpty(api.getInstanceIds()))
                .collect(Collectors.toList());
    }
}
