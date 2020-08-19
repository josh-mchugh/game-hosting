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
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDataRunner {

    private final AppConfig appConfig;

    @Autowired(required = false)
    public Map<String, ISeedService<?>> seedServices;

    @EventListener
    public void onContextRefreshedEvent(ContextRefreshedEvent event) {

        if(appConfig.isEnableSeedData()) {

            log.info("Initializing seed data...");

            seedServices.values().stream()
                    .sorted(Comparator.comparingInt(ISeedService::order))
                    .filter(ISeedService::dataNotExists)
                    .forEachOrdered(service -> {
                        log.info("Initializing {} data.", service.type());
                        ImmutableList<?> data = service.initializeData();
                        log.info("{} initialized: {}", service.type(), data.size());
                    });

            log.info("Initialization of seed data complete.");
        }
    }
}
