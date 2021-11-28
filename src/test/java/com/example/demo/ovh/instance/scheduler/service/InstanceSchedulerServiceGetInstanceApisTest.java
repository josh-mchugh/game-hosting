package com.example.demo.ovh.instance.scheduler.service;

import com.example.demo.ovh.instance.feign.InstanceFeignService;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceSchedulerServiceGetInstanceApisTest {

    @Autowired
    private InstanceSchedulerService instanceSchedulerService;

    @MockBean
    private InstanceFeignService instanceFeignService;

    @Test
    public void whenServiceIsValidThenReturnList() {

        Mockito.when(instanceFeignService.getInstances()).thenReturn(Arrays.asList(new InstanceApi(), new InstanceApi()));

        Assertions.assertEquals(ImmutableList.of(new InstanceApi(), new InstanceApi()), instanceSchedulerService.getInstanceApis());
    }
}
