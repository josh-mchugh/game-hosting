package com.example.demo.framework.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

public class SchedulerConfigTest {

    @Test
    public void whenConfigConfiguresTasksThenExpectNoError() {

        SchedulerConfig config = new SchedulerConfig();

        Assertions.assertDoesNotThrow(() -> config.configureTasks(new ScheduledTaskRegistrar()));
    }
}
