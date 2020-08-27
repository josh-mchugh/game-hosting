package com.example.demo.web.ansible;

import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.credential.CredentialClient;
import com.example.demo.awx.feign.credential.model.CredentialApi;
import com.example.demo.awx.feign.inventory.InventoryClient;
import com.example.demo.awx.feign.organization.OrganizationClient;
import com.example.demo.awx.feign.ping.PingClient;
import com.example.demo.awx.feign.ping.model.PingApi;
import com.example.demo.awx.feign.project.ProjectClient;
import com.example.demo.awx.feign.inventory.model.InventoryApi;
import com.example.demo.awx.feign.inventory.model.InventoryCreateApi;
import com.example.demo.web.ansible.model.jobs.Job;
import com.example.demo.web.ansible.model.jobs.JobRelaunchRequest;
import com.example.demo.awx.feign.organization.model.OrganizationApi;
import com.example.demo.awx.feign.project.model.ProjectApi;
import com.example.demo.awx.feign.project.model.ProjectCreateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ansible")
@RequiredArgsConstructor
public class AnsibleController {

    private final PingClient pingClient;
    private final OrganizationClient organizationClient;
    private final CredentialClient credentialClient;
    private final ProjectClient projectClient;
    private final InventoryClient inventoryClient;

    @GetMapping("/ping")
    private ResponseEntity<PingApi> getPing() {

        return new ResponseEntity<>(pingClient.getPing(), HttpStatus.OK);
    }

    @GetMapping("/organizations")
    private ResponseEntity<ListResponse<OrganizationApi>> getOrganizations() {

        return new ResponseEntity<>(organizationClient.getOrganizations(), HttpStatus.OK);
    }

    @GetMapping("/credentials/{organizationId}")
    public ResponseEntity<ListResponse<CredentialApi>> getCredentials(@PathVariable("organizationId") Long organizationId) {

        return new ResponseEntity<>(credentialClient.getCredentials(organizationId), HttpStatus.OK);
    }

    @GetMapping("/inventories")
    public ResponseEntity<ListResponse<InventoryApi>> getInventories() {

        return new ResponseEntity<>(inventoryClient.getInventories(3L), HttpStatus.OK);
    }

    @GetMapping("/inventories/create")
    public ResponseEntity<InventoryApi> postInventories() {

        InventoryCreateApi body = InventoryCreateApi.builder()
                .name("Test Inventory 1")
                .description("Test Inventory 1 from API")
                .organizationId(3L)
                .build();

        return new ResponseEntity<>(inventoryClient.createInventory(body), HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    private ResponseEntity<ListResponse<ProjectApi>> getProjects() {

        return new ResponseEntity<>(projectClient.getProjects(3L), HttpStatus.OK);
    }

    @PostMapping("/projects")
    private ResponseEntity<ProjectApi> postProject() {

        ProjectCreateApi body = ProjectCreateApi.builder()
                .name("New Test Project API 1")
                .description("New Project created from the API 1")
                .scmType("git")
                .scmUrl("git@gitlab.com:jmchugh/ansible-playbooks.git")
                .credentialId(3L)
                .build();

        return new ResponseEntity<>(projectClient.createProject(3L, body), HttpStatus.CREATED);
    }

    @GetMapping("/jobs")
    private ResponseEntity<ListResponse<Job>> getJobs(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/jobs/")
                .toUriString();

        ParameterizedTypeReference<ListResponse<Job>> type = new ParameterizedTypeReference<ListResponse<Job>>() {};

        ResponseEntity<ListResponse<Job>> jobs = getRestTemplate().exchange(url, HttpMethod.GET, null, type);

        return new ResponseEntity<>(jobs.getBody(), HttpStatus.OK);
    }

    @PostMapping("/jobs/relaunch")
    private ResponseEntity<Job> relaunchJob(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/jobs/9/relaunch/")
                .toUriString();

        ResponseEntity<Job> job = getRestTemplate().postForEntity(url, new HttpEntity<>(new JobRelaunchRequest()), Job.class);

        return new ResponseEntity<>(job.getBody(), HttpStatus.CREATED);
    }

    public RestTemplate getRestTemplate() {

        return new RestTemplateBuilder()
                .basicAuthentication("admin", "password")
                .build();
    }
}
