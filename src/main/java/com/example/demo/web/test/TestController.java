package com.example.demo.web.test;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.flavor.feign.FlavorClient;
import com.example.demo.ovh.image.feign.ImageClient;
import com.example.demo.ovh.instance.feign.InstanceClient;
import com.example.demo.ovh.instance.feign.InstanceGroupClient;
import com.example.demo.ovh.instance.feign.model.InstanceCreateApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupCreateApi;
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
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final OvhConfig ovhConfig;
    private final RegionClient regionClient;
    private final ImageClient imageClient;
    private final FlavorClient flavorClient;
    private final InstanceGroupClient instanceGroupClient;
    private final InstanceClient instanceClient;

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

    @GetMapping("/ovh/project/images")
    public ResponseEntity<?> getImages() {

        return new ResponseEntity<>(imageClient.getImages(ovhConfig.getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/image")
    public ResponseEntity<?> getImage() {

        return new ResponseEntity<>(imageClient.getImage(ovhConfig.getProjectId(), "cefc8220-ba0a-4327-b13d-591abaf4be0c"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/flavors")
    public ResponseEntity<?> getFlavors() {

        return new ResponseEntity<>(flavorClient.getFlavors(ovhConfig.getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/flavor")
    public ResponseEntity<?> getFlavor() {

        return new ResponseEntity<>(flavorClient.getFlavorById(ovhConfig.getProjectId(), "a64381e7-c4e7-4b01-9fbe-da405c544d2e"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/groups")
    public ResponseEntity<?> getGroups() {

        return new ResponseEntity<>(instanceGroupClient.getInstanceGroups(ovhConfig.getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group/create")
    public ResponseEntity<?> createGroup() {

        InstanceGroupCreateApi create = InstanceGroupCreateApi.builder()
                .name("test-group-1")
                .region("US-EAST-VA-1")
                .type("affinity")
                .build();

        return new ResponseEntity<>(instanceGroupClient.createInstanceGroup(ovhConfig.getProjectId(), create), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group")
    public ResponseEntity<?> getGroup() {

        return new ResponseEntity<>(instanceGroupClient.getInstanceGroupById(ovhConfig.getProjectId(), "84ad0d1a-d65c-49a7-b6d4-516d1465a65e"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group/delete")
    public ResponseEntity<?> deleteGroup(@RequestParam("id") String id) {

        instanceGroupClient.deleteInstanceGroupById(ovhConfig.getProjectId(), id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ovh/project/instance/create")
    public ResponseEntity<?> createInstance() {

        InstanceCreateApi request = InstanceCreateApi.builder()
                .flavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
                .name(UUID.randomUUID().toString())
                .imageId("cefc8220-ba0a-4327-b13d-591abaf4be0c")
                .region("US-EAST-VA-1")
                .groupId("84ad0d1a-d65c-49a7-b6d4-516d1465a65e")
                .build();

        return new ResponseEntity<>(instanceClient.createInstance(ovhConfig.getProjectId(), request), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/instance")
    public ResponseEntity<?> getInstance() {

        return new ResponseEntity<>(instanceClient.getInstanceById(ovhConfig.getProjectId(), "d0cf8308-f83c-451a-9f0e-b3cd95fbffd6"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/instance/delete")
    public ResponseEntity<?> deleteInstance() {

        instanceClient.deleteInstance(ovhConfig.getProjectId(), "1ed3e014-aa09-43e6-b4c8-0681ebe9935e");

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
