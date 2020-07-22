package com.example.demo.web.test;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.ovh.OvhClient;
import com.example.demo.ovh.model.GroupCreate;
import com.example.demo.ovh.model.InstanceCreate;
import com.example.demo.ovh.model.SshKeyCreate;
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

    private final AppConfig appConfig;
    private final OvhClient ovhClient;

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

    @GetMapping("/ovh/project/details")
    public ResponseEntity<?> getProjectDetails() {

        return new ResponseEntity<>(ovhClient.getProjectDetails(appConfig.getOvh().getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/images")
    public ResponseEntity<?> getImages() {

        return new ResponseEntity<>(ovhClient.getImages(appConfig.getOvh().getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/image")
    public ResponseEntity<?> getImage() {

        return new ResponseEntity<>(ovhClient.getImage(appConfig.getOvh().getProjectId(), "cefc8220-ba0a-4327-b13d-591abaf4be0c"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/flavors")
    public ResponseEntity<?> getFlavors() {

        return new ResponseEntity<>(ovhClient.getFlavors(appConfig.getOvh().getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/flavor")
    public ResponseEntity<?> getFlavor() {

        return new ResponseEntity<>(ovhClient.getFlavorById(appConfig.getOvh().getProjectId(), "a64381e7-c4e7-4b01-9fbe-da405c544d2e"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/sshkeys")
    public ResponseEntity<?> getSshKeys() {

        return new ResponseEntity<>(ovhClient.getSshKeys(appConfig.getOvh().getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/sshkey")
    public ResponseEntity<?> getSshKey() {

        return new ResponseEntity<>(ovhClient.getSshKeyById(appConfig.getOvh().getProjectId(), "5455553d"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/sshkey/create")
    public ResponseEntity<?> createSshKey() {

        SshKeyCreate create = SshKeyCreate.builder()
                .name("test-1")
                .publicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCc6KkpzN4TmJn2WOwjXyIU0jBOp2El/+U08uTw9hVj0GbMbU402HfHGQb24Bh6uNbE1RaMz9rY1Zs2D1S6evDgabSYJaFxJlMzsCdGrcq5c/BIaGG9EzmuPul/VMU59KqhKQ9HCGnHx/VUl0OjwhXmyB3cQRI7QJX9WjfMD8d/PVsIjLNpo15BwWsRVIpR3B2Bwnqt54PDO9dSWYxea2ppkQSiJJ1jtUtR7ViGLid7YkO/bbK1xMasJpzo3V+i/MGegWa1skVaTya6eiMnErxlKAuRcd0mEiM8LIsiT5xIv0uAL2ssTegtKUmH7rNGOJRHDGm1/G2BkKsBS8B70t/n me@ME-PC")
                .region("US-EAST-VA-1")
                .build();

        return new ResponseEntity<>(ovhClient.createSshKey(appConfig.getOvh().getProjectId(), create), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/sshkey/delete")
    public ResponseEntity<?> deleteSshKey() {

        ovhClient.deleteSshKeyById(appConfig.getOvh().getProjectId(), "6447567a64433078");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ovh/project/groups")
    public ResponseEntity<?> getGroups() {

        return new ResponseEntity<>(ovhClient.getGroups(appConfig.getOvh().getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group/create")
    public ResponseEntity<?> createGroup() {

        GroupCreate create = GroupCreate.builder()
                .name("test-group-1")
                .region("US-EAST-VA-1")
                .type("affinity")
                .build();

        return new ResponseEntity<>(ovhClient.createGroup(appConfig.getOvh().getProjectId(), create), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group")
    public ResponseEntity<?> getGroup() {

        return new ResponseEntity<>(ovhClient.getGroupById(appConfig.getOvh().getProjectId(), "3bf0b22f-6509-4c6a-b3ef-6719f8670f48"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group/delete")
    public ResponseEntity<?> deleteGroup() {

        ovhClient.deleteGroupById(appConfig.getOvh().getProjectId(), "c853760f-76b2-4e95-9b3b-bd85c3573492");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ovh/project/instance/create")
    public ResponseEntity<?> createInstance() {

        InstanceCreate request = InstanceCreate.builder()
                .flavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
                .name(UUID.randomUUID().toString())
                .imageId("cefc8220-ba0a-4327-b13d-591abaf4be0c")
                .region("US-EAST-VA-1")
                .groupId("3bf0b22f-6509-4c6a-b3ef-6719f8670f48")
                .build();

        return new ResponseEntity<>(ovhClient.createInstance(appConfig.getOvh().getProjectId(), request), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/instance")
    public ResponseEntity<?> getInstance() {

        return new ResponseEntity<>(ovhClient.getInstanceById(appConfig.getOvh().getProjectId(), "d0cf8308-f83c-451a-9f0e-b3cd95fbffd6"), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/instance/delete")
    public ResponseEntity<?> deleteInstance() {

        ovhClient.deleteInstance(appConfig.getOvh().getProjectId(), "1ed3e014-aa09-43e6-b4c8-0681ebe9935e");

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
