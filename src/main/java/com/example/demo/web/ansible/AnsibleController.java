package com.example.demo.web.ansible;

import com.example.demo.web.ansible.model.ListResponse;
import com.example.demo.web.ansible.model.Ping;
import com.example.demo.web.ansible.model.inventories.Inventory;
import com.example.demo.web.ansible.model.inventories.InventoryRequest;
import com.example.demo.web.ansible.model.jobs.Job;
import com.example.demo.web.ansible.model.jobs.JobRelaunchRequest;
import com.example.demo.web.ansible.model.organizations.Organization;
import com.example.demo.web.ansible.model.organizations.OrganizationRequest;
import com.example.demo.web.ansible.model.projects.Project;
import com.example.demo.web.ansible.model.projects.ProjectRequest;
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

    @GetMapping("/ping")
    private ResponseEntity<Ping> getPing(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/ping")
                .toUriString();

        return new RestTemplate().getForEntity(url, Ping.class);
    }

    @GetMapping("/organizations")
    private ResponseEntity<ListResponse<Organization>> getOrganizations(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/organizations/")
                .toUriString();

        ParameterizedTypeReference<ListResponse<Organization>> type = new ParameterizedTypeReference<ListResponse<Organization>>() {};

        ResponseEntity<ListResponse<Organization>> response = getRestTemplate().exchange(url, HttpMethod.GET, null, type);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping("/organizations")
    public ResponseEntity<Organization> postOrganizations(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/organizations/")
                .toUriString();

        OrganizationRequest request = new OrganizationRequest();
        request.setName("test organization 5");
        request.setDescription("test organization 5 from API");
        request.setMaxHosts(0);

        Organization organization = getRestTemplate().postForObject(url, new HttpEntity<>(request), Organization.class);

        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

    @GetMapping("/inventories")
    public ResponseEntity<ListResponse<Inventory>> getInventories(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/inventories/")
                .toUriString();

        ParameterizedTypeReference<ListResponse<Inventory>> type = new ParameterizedTypeReference<ListResponse<Inventory>>() {};

        ResponseEntity<ListResponse<Inventory>> inventories = getRestTemplate().exchange(url, HttpMethod.GET, null, type);

        return new ResponseEntity<>(inventories.getBody(), HttpStatus.OK);
    }

    @PostMapping("/inventories")
    public ResponseEntity<Inventory> postInventories(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/inventories/")
                .toUriString();

        InventoryRequest request = new InventoryRequest();
        request.setName("Test Inventory 1");
        request.setDescription("Test Inventory 1 from API");
        request.setOrganizationId(1);

        Inventory inventory = getRestTemplate().postForObject(url, new HttpEntity<>(request), Inventory.class);

        return new ResponseEntity<>(inventory, HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    private ResponseEntity<ListResponse<Project>> getProjects(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/projects/")
                .toUriString();

        ParameterizedTypeReference<ListResponse<Project>> type = new ParameterizedTypeReference<ListResponse<Project>>() {};

        ResponseEntity<ListResponse<Project>> projects =  getRestTemplate().exchange(url, HttpMethod.GET, null, type);

        return new ResponseEntity<>(projects.getBody(), HttpStatus.OK);
    }

    @PostMapping("/projects")
    private ResponseEntity<Project> postProject(@RequestParam("serverAddress") String serverAddress) {

        String url = UriComponentsBuilder.fromHttpUrl(serverAddress)
                .path("/api/v2/projects/")
                .toUriString();

        ProjectRequest request = new ProjectRequest();
        request.setName("New Test Project API 1");
        request.setDescription("New Project created from the API 1");
        request.setScmType("git");
        request.setScmUrl("git@gitlab.com:jmchugh/ansible-playbooks.git");
        request.setCredentialId(3L);
        request.setOrganizationId(1L);

        Project project = getRestTemplate().postForObject(url, new HttpEntity<>(request), Project.class);

        return new ResponseEntity<>(project, HttpStatus.CREATED);
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
