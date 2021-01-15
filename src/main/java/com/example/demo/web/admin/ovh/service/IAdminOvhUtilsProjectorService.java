package com.example.demo.web.admin.ovh.service;

import com.example.demo.web.admin.ovh.model.InstanceGroupStatistic;

public interface IAdminOvhUtilsProjectorService {

    InstanceGroupStatistic getInstanceGroupStatistics();

    InstanceGroupStatistic handleInstanceGroupDelete();
}
