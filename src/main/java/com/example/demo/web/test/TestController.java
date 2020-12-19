package com.example.demo.web.test;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import com.example.demo.ovh.region.feign.RegionClient;
import com.example.demo.web.test.model.Metrics;
import com.example.demo.web.test.model.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final OvhConfig ovhConfig;
    private final RegionClient regionClient;
    private final IInstanceGroupFeignService instanceGroupFeignService;

    @GetMapping("")
    public String getDefault() {

        return "index";
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> getHealth(@RequestParam("serverAddress") String serverAddress) {

        return WebClient.create(serverAddress)
                .get()
                .uri("/health")
                .retrieve()
                .toEntity(String.class);
    }

    @GetMapping("/metrics")
    public Flux<ServerSentEvent<Metrics>> streamMetricEvents(@RequestParam("serverAddress") String serverAddress) {

        return Flux.interval(Duration.ofSeconds(15))
                .map(sequence -> ServerSentEvent.<Metrics>builder()
                        .id(String.valueOf(sequence))
                        .event("event-metrics")
                        .data(getMetric(serverAddress))
                        .build());
    }

    @GetMapping("/logs")
    public Flux<ServerSentEvent<String>> streamLogEvents(@RequestParam("serverAddress") String serverAddress) {

        ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<ServerSentEvent<String>>() {};

        return WebClient.create(serverAddress)
                .get()
                .uri("/minecraft/logs")
                .retrieve()
                .bodyToFlux(type);
    }

    @GetMapping("/start")
    public Mono<ResponseEntity<String>> startMinecraft(@RequestParam("serverAddress") String serverAddress) {

        return WebClient.create(serverAddress)
                .get()
                .uri("/minecraft/start")
                .retrieve()
                .toEntity(String.class);
    }

    @GetMapping("/stop")
    public Mono<ResponseEntity<String>> stopMinecraft(@RequestParam("serverAddress") String serverAddress) {

        return WebClient.create(serverAddress)
                .get()
                .uri("/minecraft/stop")
                .retrieve()
                .toEntity(String.class);
    }

    @GetMapping("/status")
    public Flux<ServerSentEvent<Status>> statusMinecraft(@RequestParam("serverAddress") String serverAddress) {

        return Flux.interval(Duration.ofSeconds(15))
                .map(sequence -> ServerSentEvent.<Status>builder()
                        .id(String.valueOf(sequence))
                        .event("event-status")
                        .data(getStatus(serverAddress))
                        .build());
    }

    @GetMapping("/ovh/project/regions")
    public ResponseEntity<?> getRegions() {

        return new ResponseEntity<>(regionClient.getRegions(ovhConfig.getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/region")
    public ResponseEntity<?> getRegion() {

        return new ResponseEntity<>(regionClient.getRegion(ovhConfig.getProjectId(), "US-EAST-VA-1"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/availableRegions")
    public ResponseEntity<?> getAvailableRegions() {

        return new ResponseEntity<>(regionClient.getAvailableRegions(ovhConfig.getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/groups")
    public ResponseEntity<?> getGroups() {

        return new ResponseEntity<>(instanceGroupFeignService.getInstanceGroups(), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group/delete")
    public ResponseEntity<?> deleteGroup(@RequestParam("id") String id) {

        instanceGroupFeignService.deleteInstanceGroupById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Metrics getMetric(String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/metric")
                .toUriString();

        return new RestTemplate().getForObject(url, Metrics.class);
    }

    private Status getStatus(String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/minecraft/status")
                .toUriString();

        return new RestTemplate().getForObject(url, Status.class);
    }
}
