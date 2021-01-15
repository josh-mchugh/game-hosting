package com.example.demo.web.admin.ovh.service;

import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;

public class AdminOvhUtilsDeleteInstanceGroupStatisticsTest {

    @Test
    public void whenOneInstanceGroupExistsAndDoesNotHaveInstanceIdsThenReturnZero() {

        IInstanceGroupFeignService instanceGroupFeignService = Mockito.mock(IInstanceGroupFeignService.class);

        Mockito.when(instanceGroupFeignService.getInstanceGroups())
                .thenReturn(Collections.singletonList(new InstanceGroupApi()))
                .thenReturn(new ArrayList<>());

        Mockito.doNothing().when(instanceGroupFeignService).deleteInstanceGroupById(Mockito.anyString());

        AdminOvhUtilsService service = new AdminOvhUtilsService(instanceGroupFeignService);

        Assertions.assertEquals(0, service.handleInstanceGroupDelete().getTotal());
    }

    @Test
    public void whenOneInstanceGroupExistsAndDoesNotHaveInstanceIdsThenExpectDeleteCalled() {

        IInstanceGroupFeignService instanceGroupFeignService = Mockito.mock(IInstanceGroupFeignService.class);

        Mockito.when(instanceGroupFeignService.getInstanceGroups()).thenReturn(Collections.singletonList(new InstanceGroupApi()));
        Mockito.doNothing().when(instanceGroupFeignService).deleteInstanceGroupById(Mockito.anyString());

        AdminOvhUtilsService service = new AdminOvhUtilsService(instanceGroupFeignService);

        service.handleInstanceGroupDelete();

        Mockito.verify(instanceGroupFeignService, Mockito.times(1)).deleteInstanceGroupById(Mockito.any());
    }

    @Test
    public void whenInstanceGroupsDoNotExistsThenExpectZeroTotal() {

        IInstanceGroupFeignService instanceGroupFeignService = Mockito.mock(IInstanceGroupFeignService.class);

        Mockito.when(instanceGroupFeignService.getInstanceGroups()).thenReturn(new ArrayList<>());

        AdminOvhUtilsService service = new AdminOvhUtilsService(instanceGroupFeignService);

        Assertions.assertEquals(0, service.handleInstanceGroupDelete().getTotal());
    }

    @Test
    public void whenInstanceGroupsExistAndDoHaveInstanceIdsThenReturnZero() {

        IInstanceGroupFeignService instanceGroupFeignService = Mockito.mock(IInstanceGroupFeignService.class);

        InstanceGroupApi instanceGroupApi = new InstanceGroupApi();
        instanceGroupApi.setInstanceIds(Collections.singletonList("id"));

        Mockito.when(instanceGroupFeignService.getInstanceGroups()).thenReturn(Collections.singletonList(instanceGroupApi));
        Mockito.doNothing().when(instanceGroupFeignService).deleteInstanceGroupById(Mockito.anyString());

        AdminOvhUtilsService service = new AdminOvhUtilsService(instanceGroupFeignService);

        Assertions.assertEquals(0, service.handleInstanceGroupDelete().getTotal());
    }
}
