package com.example.demo.framework.seed;

import com.example.demo.framework.properties.AppConfig;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDataRunnerImpl {

    private final AppConfig appConfig;

    @Autowired(required = false)
    public Map<String, SeedService<?>> seedServices;

    @EventListener
    public void onContextRefreshedEvent(ContextRefreshedEvent event) throws ExecutionException, InterruptedException {

        if(appConfig.isEnableSeedData()) {

            log.info("Initializing seed data...");

            List<SeedService<?>> sortedServices = seedServices.values().stream()
                    .sorted(Comparator.comparingInt(SeedService::order))
                    .collect(Collectors.toList());

            for (SeedService<?> service : sortedServices) {

                if(service.dataNotExists()) {

                    log.info("{} - Initializing {} data.", service.order(), service.type());
                    ImmutableList<?> data = service.initializeData();
                    log.info("{} initialized: {}", service.type(), data.size());
                }
            }

            log.info("Initialization of seed data complete.");
        }
    }
}
