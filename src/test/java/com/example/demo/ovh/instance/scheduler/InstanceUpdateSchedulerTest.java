package com.example.demo.ovh.instance.scheduler;

import com.example.demo.ovh.instance.scheduler.service.InstanceSchedulerService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class InstanceUpdateSchedulerTest {

    @Test
    public void whenInstanceUpdateSchedulerExecutes() throws ExecutionException, InterruptedException {

        InstanceSchedulerService service = Mockito.mock(InstanceSchedulerService.class);
        Mockito.when(service.handleInstanceUpdates(Mockito.any())).thenReturn(Lists.newArrayList(UUID.randomUUID()));

        InstanceUpdateScheduler scheduler = new InstanceUpdateScheduler(service);

        Assertions.assertDoesNotThrow(scheduler::instanceUpdateScheduler);
    }
}
