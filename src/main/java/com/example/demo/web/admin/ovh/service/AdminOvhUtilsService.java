package com.example.demo.web.admin.ovh.service;

import com.example.demo.web.admin.ovh.model.InstanceGroupStatistic;

public interface AdminOvhUtilsService {

    InstanceGroupStatistic getInstanceGroupStatistics();

    InstanceGroupStatistic handleInstanceGroupDelete();
}
